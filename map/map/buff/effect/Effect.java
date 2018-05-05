package map.buff.effect;

import cfg.Const;
import cfg.buff.*;
import cfg.buff.AbnormalDamageUp;
import cfg.buff.AddHPByLevelInterval;
import cfg.buff.Disperse;
import cfg.buff.Reflect;
import cfg.buff.SkillEffect;
import map.agent.Fighter;
import map.msg.EffectInfo;

import java.util.List;

public abstract class Effect{
	private final int id;
	private final int groupid;
	private final int priority;
	private final int maxOverlay;
	private final int level;
	private final boolean needNofity;
    private final boolean isHarmful;
	protected Fighter target;
	protected final Fighter caster;

    private int overlay;
	private final int endType;
	private long endTime;

    private boolean clearWhileDead;

    private final static int BASE_NULL_GROUP_ID = 1 << 20;
	
	public Effect(int id, int groupid, int priority, int maxOverlay, int level, boolean needNofity, boolean isHarmful, Fighter caster, int endType, long endTime) {
		this.id = id;
		this.groupid = groupid != Const.NULL ? groupid : BASE_NULL_GROUP_ID + id; // 如果group为null,取一个特殊的惟一id
		this.priority = priority;
		this.maxOverlay = Math.max(1, maxOverlay);
		this.level = level;
		this.needNofity = needNofity && endType != EndCondition.BY_NEXT_ATTACK;
        this.isHarmful = isHarmful;
		this.caster = caster;
        this.overlay = 1;
        this.endType = endType;
        this.endTime = endTime;

        this.clearWhileDead = false;
	}

    public boolean isClearWhileDead() {
        return clearWhileDead;
    }

    public void setClearWhileDead(boolean clearWhileDead) {
        this.clearWhileDead = clearWhileDead;
    }

    public final int getId() {
		return id;
	}

	public final int getGroupid() {
		return groupid;
	}

	public final int getPriority() {
		return priority;
	}

	public final Fighter getCaster() {
		return caster;
	}

	public final boolean isNeedNotify() {
		return needNofity;
	}

    public final int getOverlay() {
        return overlay;
    }

	public final long getEndTime() {
		return endTime;
	}

    public void refreshBy(Effect other) {
        //this.endType = other.endType;
        this.endTime = other.endTime;
    }

	public final int getSkillLevel() {
		return level;
	}

    public final boolean isHarmful() {
        return this.isHarmful;
    }

	public boolean isTransient() {
		return endType == cfg.buff.EndCondition.BY_TRANSIENT;
	}

    public final boolean isEndByTime() {
        return endType == EndCondition.BY_TIME;
    }

    public final boolean isEndByAttack() {
        return endType == EndCondition.BY_NEXT_ATTACK;
    }

    public boolean addOverlay(int add) {
        if(overlay < maxOverlay) {
            overlay = Math.min(overlay + add, maxOverlay);
            return true;
        } else {
            return false;
        }
    }

    public boolean decOverlayAndCheckGreaterZero() {
        return --overlay > 0;
    }
	
	public void install(Fighter target) {
		this.target = target;
//		switch(this.endType) {
//			case cfg.buff.EndCondition.BY_TIME: {
//				break;
//			}
//			case cfg.buff.EndCondition.BY_NEXT_ATTACK: {
//				evtid = Listener.SKILL_ATTACKER_END;
//				listener = (go, evtid, param) -> {
//					target.getBuffMgr().removeEffect(this);
//				};
//				break;
//			}
//			case cfg.buff.EndCondition.BY_TRANSIENT: {
//				// 瞬时性的,立即结束啥也不做
//				break;
//			}
//			case cfg.buff.EndCondition.BY_REMOVE: {
//				break;
//			}
//			default: {
//				throw new RuntimeException("unknown Effect EndCondition:" + this.endType);
//			}
//		}
//		if(listener != null) {
//			target.addListener(evtid, listener);
//		}
		onInstall();
	}
	
	public void uninstall() {
	    if(this.target != null) {
            onUninstall();
            this.target = null;
        }
	}

	protected void onInstall() {}
	protected void onUninstall() {}
	protected boolean onUpdate(long now) {
		return true;
	}

	public final boolean update(long now) {
		return onUpdate(now) && (this.endType != EndCondition.BY_TIME || now < endTime);
	}

	public EffectInfo createInfo() {
		final EffectInfo bi = new EffectInfo();
		bi.id = getId();
		bi.level = level;
        bi.overlaynum = overlay;
		return bi;
	}
	
	public static Effect createEffect(cfg.buff.Effect effect, int endType, List<Float> duration, int skillLevel, Fighter caster) {
        final long endTime = System.currentTimeMillis() + (long)(common.Utils.getOrLast(duration, skillLevel - 1) * 1000L);
        switch (effect.getTypeId()) {
            case cfg.buff.AddProperty.TYPEID: {
                return new map.buff.effect.AddProperty((cfg.buff.AddProperty) effect, caster, endType, endTime);
            }
            case cfg.buff.Bleed.TYPEID: {
                return new map.buff.effect.Bleed((cfg.buff.Bleed) effect, caster, endType, endTime);
            }
            case cfg.buff.BleedPercent.TYPEID: {
                return new map.buff.effect.BleedPercent((cfg.buff.BleedPercent) effect, caster, endType, endTime);
            }
            case cfg.buff.AddHPInterval.TYPEID: {
                return new AddHPInterval((cfg.buff.AddHPInterval)effect, caster, endType, endTime);
            }
            case AddHPByLevelInterval.TYPEID: {
                return new map.buff.effect.AddHPByLevelInterval((cfg.buff.AddHPByLevelInterval)effect, skillLevel, caster, endType, endTime);
            }
            case cfg.buff.AddMPInterval.TYPEID: {
                return new AddMPInterval((cfg.buff.AddMPInterval)effect, caster, endType, endTime);
            }
            case cfg.buff.AddMPPercentInterval.TYPEID: {
                return new AddMPPercentInterval((cfg.buff.AddMPPercentInterval)effect, caster, endType, endTime);
            }
            case cfg.buff.AddMPByLevelInterval.TYPEID: {
                return new map.buff.effect.AddMPByLevelInterval((cfg.buff.AddMPByLevelInterval)effect, skillLevel, caster, endType, endTime);
            }
            case cfg.buff.AddHPPercentInterval.TYPEID: {
                return new AddHPPercentInterval((cfg.buff.AddHPPercentInterval)effect, caster, endType, endTime);
            }
            case cfg.buff.SetAbnormalState.TYPEID: {
                return new map.buff.effect.SetAbnormalState((cfg.buff.SetAbnormalState) effect, caster, endType, endTime);
            }
            case cfg.buff.AddPropertyByLevel.TYPEID: {
                return new map.buff.effect.AddPropertyByLevel((cfg.buff.AddPropertyByLevel)effect, skillLevel, caster, endType, endTime);
            }
            case cfg.buff.LacerationbyLevel.TYPEID: {
                return new map.buff.effect.LacerationbyLevel((cfg.buff.LacerationbyLevel)effect, skillLevel, caster, endType, endTime);
            }
            case cfg.buff.Aura.TYPEID: {
                return new map.buff.effect.Aura((cfg.buff.Aura)effect, skillLevel, caster, endType, endTime);
            }
            case cfg.buff.SuckBlood.TYPEID: {
                return new map.buff.effect.SuckBlood((cfg.buff.SuckBlood)effect, skillLevel, caster, endType, endTime);
            }
            case Reflect.TYPEID: {
                return new map.buff.effect.Reflect((cfg.buff.Reflect)effect, skillLevel, caster, endType, endTime);
            }
            case Disperse.TYPEID: {
                return new map.buff.effect.Disperse((cfg.buff.Disperse)effect, caster, endType, endTime);
            }
            case AbnormalDamageUp.TYPEID: {
                return new map.buff.effect.AbnormalDamageUp((cfg.buff.AbnormalDamageUp)effect, skillLevel, caster, endType, endTime);
            }
            case SkillEffect.TYPEID: {
                return new map.buff.effect.SkillEffect((cfg.buff.SkillEffect)effect, skillLevel, caster, endType, endTime);
            }
            default: {
                throw new RuntimeException("unknown effect:" + effect);
            }
        }
	}
}
