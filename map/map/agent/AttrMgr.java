package map.agent;

import cfg.CfgMgr;
import cfg.Const;
import cfg.fight.AttrId;
import common.AttrUtils;
import map.buff.factor.Context;
import map.buff.factor.Factor;
import map.map.GameMap;
import map.msg.SChangeAttrs;
import map.msg.SChangeHp;
import xdb.Trace;

import java.util.*;

import static cfg.fight.AttrId.*;


public final class AttrMgr {
	private final Fighter self;
    private boolean alwaysBroadcast;

    private final boolean notifyMp;
	private final float[] originRawAttrs = AttrUtils.newRawAttrs();
	private final float[] rawAttrs = AttrUtils.newRawAttrs();
	private final float[] finalAttrs = AttrUtils.newFinalAttrs();
	private final FactorProperty[] factorAttrs = new FactorProperty[CALC_ATTR_NUM - EXT_ATTR_END];
    private final static long sendHpInterval = (long)(CfgMgr.roleconfig.hpbroadcastinterval * 1000);
	
	public AttrMgr(Fighter self) {
		this.self = self;
        this.notifyMp = self.isPlayer();
        this.alwaysBroadcast = false;
	}

    public void setAlwaysBroadcast(boolean alwaysBroadcast) {
        this.alwaysBroadcast = alwaysBroadcast;
    }

    public void init(List<Float> originAttrs) {
		assert(originAttrs.size() == EXT_ATTR_END);
		AttrUtils.fill(this.originRawAttrs, originAttrs);
		System.arraycopy(this.originRawAttrs, 0, this.rawAttrs, 0, this.rawAttrs.length);
		calcAttrs();
		for(int i = EXT_ATTR_END + 1 ; i < CALC_ATTR_NUM ; i++) {
			factorAttrs[i - EXT_ATTR_END] = new FactorProperty();
		}
	}

	public void initHpMp(int hp, int mp) {
        this.finalAttrs[HP_VALUE] = hp != Const.NULL ? Math.min(hp, finalAttrs[HP_FULL_VALUE]) : finalAttrs[HP_FULL_VALUE];
        this.finalAttrs[MP_VALUE] = mp != Const.NULL ? Math.min(mp, finalAttrs[MP_FULL_VALUE]) : finalAttrs[MP_FULL_VALUE];
    }

	public void changeAttrs(List<Float> originAttrs, boolean resethpmp) {
		assert(originAttrs.size() == this.rawAttrs.length);
		final float[] oldOriginRawAttrs = Arrays.copyOf(originRawAttrs, originRawAttrs.length);
		AttrUtils.fill(this.originRawAttrs, originAttrs);

		final ArrayList<Integer> modifyAttrs = new ArrayList<>();
		for(int i = 0 ; i < this.rawAttrs.length ; i++) {
			final float delta = this.originRawAttrs[i] - oldOriginRawAttrs[i];
			if(delta != 0) {
				this.rawAttrs[i] += delta;
                AttrUtils.calcAttr(this.rawAttrs, finalAttrs, i, modifyAttrs);
			}
		}

		for (int attrid : modifyAttrs) {
			checkAttr(attrid);
		}

		if(resethpmp) {
			resetHPMP();
		}
	}

	final void calcAttrs() {
		for(int attrid = 0 ; attrid < BASE_ATTR_END ; attrid++) {
            AttrUtils.calcAttr(rawAttrs, finalAttrs, attrid, null);
		}
	}

    //private final static int[] ALL_ATTRIDS = new int[] {HP_VALUE, HP_FULL_VALUE, MP_VALUE, MP_FULL_VALUE, MODEL_SCALE, MOVE_SPEED};
    private final static int[] PLAYER_NOTIFY_ATTRIDS = new int[] {HP_FULL_VALUE, MP_VALUE, MP_FULL_VALUE, MODEL_SCALE, MOVE_SPEED};
    private final static int[] NOT_PLAYER_NOTIFY_ATTRIDS = new int[] {HP_FULL_VALUE, MODEL_SCALE, MOVE_SPEED};

	public void fillAttrs(Map<Integer, Float> attrs) {
        for(int id : notifyMp ? PLAYER_NOTIFY_ATTRIDS : NOT_PLAYER_NOTIFY_ATTRIDS) {
            attrs.put(id, finalAttrs[id]);
        }
        attrs.put(HP_VALUE, finalAttrs[HP_VALUE]);
	}

    public void sendSelfOneAttrChange(int attrid) {
        if(!self.isActive()) return;
        final SChangeAttrs msg = new SChangeAttrs();
        msg.attrs.put(attrid, getFinalAttr(attrid));
        ((Player)self).send(self.getAid(), msg);
    }

	public final void addFactor(int pid, Factor factor) {
		factorAttrs[pid - EXT_ATTR_END].add(factor);
	}
	
	public final void removeFactor(int pid, long fid) {
		factorAttrs[pid - EXT_ATTR_END].remove(fid);
	}


	public final double getFactorValue(int pid, Context ctx, Factor onceFactor) {
		return factorAttrs[pid - EXT_ATTR_END].comput(onceFactor, ctx);
	}

	public final float getFinalAttr(int pid) {
		return finalAttrs[pid];
	}

	public final void addRawAttr(int attrid, double add) {
		switch (attrid) {
			case HP_VALUE: {
				addHPValue((int)add);
				return;
			}
			case HP_VALUE_PERCENT: {
				addHPValue((int)(getHPFullValue() * add));
				return;
			}
			case MP_VALUE: {
				addMPValue((int)add);
				return;
			}
			case MP_VALUE_PERCENT: {
				addMPValue((int)(getMPFullValue() * add));
				return;
			}
		}
		rawAttrs[attrid] += add;
		final ArrayList<Integer> modifyAttrs = new ArrayList<>();
        AttrUtils.calcAttr(rawAttrs, finalAttrs, attrid, modifyAttrs);
		for(int aid : modifyAttrs) {
			checkAttr(aid);
		}
	}

    public final void setRawAttr(int attrid, float value) {
        if(rawAttrs[attrid] == value) return;
        rawAttrs[attrid] = value;
        final ArrayList<Integer> modifyAttrs = new ArrayList<>();
        AttrUtils.calcAttr(rawAttrs, finalAttrs, attrid, modifyAttrs);
        for(int aid : modifyAttrs) {
            checkAttr(aid);
        }
    }

	private void checkAttr(int attrid) {
		switch (attrid) {
			case MOVE_SPEED:
            case MODEL_SCALE: {
                break;
            }
			case HP_FULL_VALUE: {
				if(finalAttrs[HP_VALUE] > finalAttrs[HP_FULL_VALUE]) {
					finalAttrs[HP_VALUE] = finalAttrs[HP_FULL_VALUE];
					//modifyHp(); 满的情况下通知无意义
                }
				break;
			}
			case MP_FULL_VALUE:{
				if(finalAttrs[MP_VALUE] > finalAttrs[MP_FULL_VALUE]) {
					finalAttrs[MP_VALUE] = finalAttrs[MP_FULL_VALUE];
					//modifyAttr(MP_VALUE); 满的情况下通知无意义
                }
                if(notifyMp)
                    sendSelfOneAttrChange(MP_FULL_VALUE);
				break;
			}
            default:
                throw new RuntimeException("unknown checkAttr attrid:" + attrid);
		}
        modifyAttr(attrid);
	}

	private boolean change = false;
    private long dirtyFlag = 0;

    private boolean setDirty(int attrid) {
        if((dirtyFlag & (1L << attrid)) != 0) return false;
        dirtyFlag |= (1L << attrid);
        return true;
    }

    private boolean isDirty(int attrid) {
        return (dirtyFlag & (1L << attrid)) != 0;
    }

	public final void modifyAttr(int attrid) {
		if(setDirty(attrid) && !change) {
            change = true;
            self.addDeferTask(this::doBroadcastAttrs);
		}
	}

    private void doBroadcastAttrs() {
        change = false;
        final SChangeAttrs re = new SChangeAttrs();
        for (int id : PLAYER_NOTIFY_ATTRIDS) {
            if (isDirty(id))
                re.attrs.put(id, getFinalAttr(id));
        }
        self.broadcastToNearby(re);
        dirtyFlag = 0;
    }

    private long lastSendHpTime;
    private boolean dirtyHp;
    private void modifyHp() {
        if(!dirtyHp) {
            dirtyHp = true;
            self.schedule(this::doBroadcastHp, lastSendHpTime + sendHpInterval - self.getNow());
        }
    }

    private void doBroadcastHp() {
        dirtyHp = false;
        lastSendHpTime = self.getNow();
        final int hp = (int)getHPValue();

        final HashSet<Player> receivers = new HashSet<>();
        if(alwaysBroadcast) {
            for (Agent.SubscriptMeInfo si : self.getSubscriptMePlayers().values()) {
                if (!si.player.isKnownHp(self, hp))
                    receivers.add(si.player);
            }
        } else {
            for (Agent.SubscriptMeInfo si : self.getSubscriptMePlayers().values()) {
                if (!si.player.isKnownHp(self, hp) && (si.player.isRecentMeAttack(self, GameMap.ATTACK_EXPIRE_TIME) || self.getOwner() == si.player))
                    receivers.add(si.player);
            }
        }
        GameMap.send(receivers, self, new SChangeHp(hp));
    }

	public void resetHPMP() {
		if(finalAttrs[HP_VALUE] != finalAttrs[HP_FULL_VALUE]) {
			finalAttrs[HP_VALUE] = finalAttrs[HP_FULL_VALUE];
			modifyHp();
		}
		if(finalAttrs[MP_VALUE] != finalAttrs[MP_FULL_VALUE]) {
			finalAttrs[MP_VALUE] = finalAttrs[MP_FULL_VALUE];
            if(notifyMp)
                sendSelfOneAttrChange(MP_VALUE);
		}
	}

	/*
	 * 所有属性接口
	 */
	
	public final float getHPValue() {
		return finalAttrs[HP_VALUE];
	}
	
	public final float addHPValue(float value) {
        if(value > 0 && !self.canBeheal()) {
            Trace.debug("addHpValue value:{} fail. {} can't be heal", value, self);
            return getHPValue();
        }
		final float oldHp = getHPValue();
        float newHp = oldHp + value;
		if(newHp > finalAttrs[HP_FULL_VALUE]) {
            newHp = finalAttrs[HP_FULL_VALUE];
		} else if(newHp < 0) {
			newHp = 0;
		}
		if(oldHp != newHp) {
            finalAttrs[HP_VALUE] = newHp;
			self.trigger(Listener.HP_CHANGE, null);
			modifyHp();
		}
		return newHp;
	}
	
	public final float getMPValue() {
		return finalAttrs[MP_VALUE];
	}
	
	public final float addMPValue(int value) {
		final float oldMp = finalAttrs[MP_VALUE];
		float newMp = oldMp + value;
		if(newMp > finalAttrs[MP_FULL_VALUE]) {
			newMp = (int)finalAttrs[MP_FULL_VALUE];
		} else if(newMp < 0) {
            newMp = 0;
		}
		if(oldMp != newMp) {
            finalAttrs[MP_VALUE] = newMp;
            if(notifyMp)
                sendSelfOneAttrChange(MP_VALUE);
		}
		return newMp;
	}

    public final boolean checkCostMpBySkill(int cost) {
        if(finalAttrs[MP_VALUE] < cost) return false;
        finalAttrs[MP_VALUE] -= cost;
        return true;
    }

    public final float getRawMoveSpeed() {
        return rawAttrs[AttrId.MOVE_SPEED];
    }
	
	public final float getAttackValueMin() {
		return getFinalAttr(ATTACK_VALUE_MIN);
	}
	
	public final float getAttackValueMax() {
		return getFinalAttr(ATTACK_VALUE_MAX);
	}
	
	public final float getExcellentRate() {
		return getFinalAttr(EXCELLENT_RATE);
	}
	
	public final float getExcellentValue() {
		return getFinalAttr(EXCELLENT_VALUE);
	}
	
	public final float getExcellentResistRate() {
		return getFinalAttr(EXCELLENT_RESIST_RATE);
	}
	
	public final float getExcellentResistValue() {
		return getFinalAttr(EXCELLENT_RESIST_VALUE);
	}
	
	public final float getCritRate() {
		return getFinalAttr(CRIT_RATE);
	}
	
	public final float getCritResitRate() {
		return getFinalAttr(CRIT_RESIST_RATE);
	}
	
	public final float getCritValue() {
		return getFinalAttr(CRIT_VALUE);
	}
	
	public final float getCritResistValue() {
		return getFinalAttr(CRIT_RESIST_VALUE);
	}
	
	public final float getDefence() {
		return getFinalAttr(DEFENCE);
	}
	
	public final float getAttackMultiRate() {
		return getFinalAttr(ATTACK_MULTI_RATE);
	}
	
	public final float getDefenceMultiRate() {
		return getFinalAttr(DEFENCE_MUTLI_RATE);
	}

    public final float getAbnormalHitRate() {
        return getFinalAttr(ABNORMAL_HIT_RATE);
    }

    public final float getAbnormalResistRate() {
        return getFinalAttr(ABNORMAL_RESIST_RATE);
    }
	
	public final int getHPFullValue() {
		return (int)getFinalAttr(HP_FULL_VALUE);
	}
	
	public final int getMPFullValue() {
		return (int)getFinalAttr(MP_FULL_VALUE);
	}
	
	public final float getHitRate() {
		return getFinalAttr(HIT_RATE);
	}
	
	public final float getHitResitRate() {
		return getFinalAttr(HIT_RESIST_RATE);
	}
	
	public final float getMoveSpeed() {
		return getFinalAttr(MOVE_SPEED);
	}
	
	public final float getHPHealRate() {
		return getFinalAttr(HP_HEAL_RATE);
	}
	
	public final float getLuckyValue() {
		return getFinalAttr(LUCKY_VALUE);
	}

    public final float getModelScale() { return getFinalAttr(MODEL_SCALE); }

    public final float getAdditionalDamage() {
        return getFinalAttr(ADDITIONAL_DAMAGE);
    }

    public final float getDamageToHuman() {
        return getFinalAttr(DAMAGE_TO_HUMAN);
    }

    public final float getDamageToPet() {
        return getFinalAttr(DAMAGE_TO_PET);
    }

    public final float getResistDamageFromHuman() {
        return getFinalAttr(RESIST_DAMAGE_FROM_HUMAN);
    }

    public final float getResistDamageFromPet() {
        return getFinalAttr(RESIST_DAMAGE_FROM_PET);
    }


	public void onDead() {

	}

	public void onRevive() {
		change = false;
		dirtyFlag = 0;
		finalAttrs[HP_VALUE] = finalAttrs[HP_FULL_VALUE];
		finalAttrs[MP_VALUE] = finalAttrs[MP_FULL_VALUE];
	}
}
