package map.agent;

import cfg.fight.AbilityType;
import cfg.fight.AttrId;
import cfg.fight.Relation;
import common.ErrorCode;
import map.MapModule;
import map.MapUtils;
import map.buff.factor.Context;
import map.buff.factor.Factor;
import map.buff.factor.Prioritys;
import map.map.GameMap;
import map.msg.*;
import map.msg.Vector3;
import map.skill.DamageParam;
import map.skill.DeathParam;
import map.skill.Skill;
import pathfinding.*;
import xdb.Trace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public abstract class Fighter extends Agent {

	public Fighter(GameMap map, FighterBuilder b) {
		super(map, b.basic);

		camp = b.camp;
		dead = false;
		isrevive = false;
		isborn = false;
        lastFightTime = 0;
        deathTime = 0;

		moveMgr = new MoveMgr(this);
		skillMgr = new SkillMgr(this);
		attrMgr = new AttrMgr(this);
		buffMgr = new BuffMgr(this);
		stateMgr = new StateMgr(this);
		slotMgr = new NearbySlotMgr(this);
        hostilityMgr = new HostilityMgr(this);
        ownPlayer = isPlayer() ? (Player)this : null;
        notifyBeSkillAttack = ownPlayer != null && map.isUseBroadcastPolicy();

        setIdleTime(map.getNow() + 30 * 1000L);
	}

	final MoveMgr moveMgr;
	final SkillMgr skillMgr;
	final AttrMgr attrMgr;
	final BuffMgr buffMgr;
	final StateMgr stateMgr;
	final NearbySlotMgr slotMgr;
    final HostilityMgr hostilityMgr;

    final Player ownPlayer;
    final boolean notifyBeSkillAttack;

	private boolean dead;
	private boolean isrevive;
	private boolean isborn;
    protected long deathTime;

    private long lastFightTime;

	private int camp;

    public abstract int getLevel();

	public boolean isRevive() {
		return isrevive;
	}
	public void setRevive() {
		isrevive = true;
        deathTime = 0;
	}
	public void clearRevive() {
		isrevive = false;
	}

	public boolean isBorn() {
		return isborn;
	}
	public void setBorn() {
		isborn = true;
	}
	public void clearBorn() {
		isborn = false;
	}

	public final MoveMgr getMoveMgr() {
		return moveMgr;
	}
	public final SkillMgr getSkillMgr() {
		return skillMgr;
	}
	public final AttrMgr getAttrMgr() {
		return attrMgr;
	}
	public final BuffMgr getBuffMgr() {
		return buffMgr;
	}
	public final StateMgr getStateMgr() {
		return stateMgr;
	}
	public final NearbySlotMgr getSlotMgr() {
		return slotMgr;
	}
	public HostilityMgr getHostilityMgr() { return hostilityMgr; }

	public int getCamp() { return camp; }
    public void setCamp(int camp) {
        this.camp = camp;
    }

    public final boolean isRelationCamp(Fighter other, int relation) {
        if(relation == Relation.SELF) {
            return this == other;
        } else {
            return MapModule.campRelations[getCamp()][other.getCamp()]  == relation;
        }
    }

    @Override
    public final double getBodyRadius() {
        return bodyRadius * (1 + attrMgr.getModelScale());
    }
    @Override
    public final double getBodyHeight() {
        return bodyHeight * (1 + attrMgr.getModelScale());
    }

    @Override
    public final double getSpeed() {
        return attrMgr.getMoveSpeed();
    }

    public final boolean isFighting() {
        return lastFightTime + 15 * 1000L > getNow();
    }

    public boolean isMyTeamMate(Fighter other) {
        return false;
    }

    @Override
    public Fighter getOwner() {
        return this;
    }

    public void setInFight() {
        this.lastFightTime = getNow();
    }

    private ArrayList<BeAttackResult> attacks = new ArrayList<>();
    private ArrayList<BeHealResult> heals = new ArrayList<>();
    public void addBeAttack(AttackResult attack) {
        if(ownPlayer != null) {
            checkSendBeSkillAttack();
            attacks.add(new BeAttackResult(attack.ismiss, attack.iscrit, attack.isexcellent, attack.islucky, attack.attack));
        }
    }

    public void addBeHeal(HealResult heal) {
        if(ownPlayer != null) {
            checkSendBeSkillAttack();
            heals.add(new BeHealResult(heal.heal));
        }
    }

    private long lastSendBeAttackTime;
    private long sendBeAttackInterval = 1000;
    private void checkSendBeSkillAttack() {
        if(attacks.isEmpty() && heals.isEmpty()) {
            schedule(() -> {
                final int hp = (int)attrMgr.getHPValue();
                final SBeSkillAttack msg = new SBeSkillAttack(attacks, heals, hp);
                ownPlayer.updateKnownHp(this, hp);
                attacks = new ArrayList<>();
                heals = new ArrayList<>();
                ownPlayer.send(getAid(), msg);
                lastSendBeAttackTime = getNow();
            }, lastSendBeAttackTime + sendBeAttackInterval - getNow());
        }
    }

	public final boolean isDead() {
		return dead;
	}
	public final void setDead() {
		this.dead = true;
        this.deathTime = getNow();
	}
	public final void clearDead() {
		this.dead = false;
	}

    public final Player getOriginPlayer() {
        if(isPlayerOrFakePlayer())
            return (Player)this;
        if(isPet())
            return ((APet)this).getOwner();
        return null;
    }
	
	public int decHPAndCheckDead(Context ctx, double damage) {
		return decHPAndCheckDead(ctx, new Factor(Prioritys.ADD) {
			@Override
			public double calc(double curValue, Context ctx) {
				return curValue + damage;
			}
			
		});
	}

	
	public int decHPAndCheckDead(Context ctx, Factor factor) {
        if(isDead() || !canBeattacked()) return 0;
		final int damage = (int) attrMgr.getFactorValue(AttrId.HP_DEC, ctx, factor);
        final DamageParam dp = new DamageParam(ctx.attacker, this, ctx, damage);
		if(attrMgr.addHPValue(-damage) <= 0) {
            setDead();
            addDeferTask(() -> kill(ctx.attacker));
		}
        trigger(Listener.BE_DAMAGE, dp);
        ctx.attacker.trigger(Listener.DO_DAMAGE, dp);
		return damage;
	}

    public float addHp(int heal) {
        if(isDead()) return 0;
        final float oldHp = attrMgr.getHPValue();
        return attrMgr.addHPValue(heal) - oldHp;
    }

	public void forceKill() {
		if(!isActive() || isDead()) return;
		attrMgr.addHPValue(-attrMgr.getHPValue());
		setDead();
		addDeferTask(() -> kill(this));
	}
	
	public boolean canSkillattack() {
		return stateMgr.isEnable(cfg.fight.AbilityType.SKILL_ATTACK);
	}

	public boolean canNormalattack() {
		return stateMgr.isEnable(AbilityType.NORMAL_ATTACK);
	}

	public boolean canAI() {
		return stateMgr.isEnable(AbilityType.AI);
	}
    public boolean canBeheal() {
        return stateMgr.isEnable(AbilityType.BEHEAL);
    }

    public boolean shouleBeheal() {
        return attrMgr.getHPValue() < attrMgr.getHPFullValue() && canBeheal();
    }

    public boolean canImmuneDebuff() {
        return !stateMgr.isEnable(AbilityType.NOT_IMMUNE_DEBUFF);
    }

	public boolean canBeattacked() {
		return stateMgr.isEnable(cfg.fight.AbilityType.BEATTACKED);
	}

    @Override
	public final boolean canMove() {
		return stateMgr.isEnable(cfg.fight.AbilityType.MOVE);
	}

	public void kill(Fighter attacker) {
		onDead(attacker);
		afterDead(attacker);
	}

	protected void onDead(Fighter attacker) {
		moveMgr.onDead();
		slotMgr.onDead();
		attrMgr.onDead();
		buffMgr.onDead();
		skillMgr.onDead();
        hostilityMgr.onDead();
	}

	protected void afterDead(Fighter attacker) {
		trigger(Listener.DEATH, new DeathParam(attacker, this));
        if (this.isMonster()) {
            Monster monster = (Monster) this;
            Set<Long> attackRoles = new HashSet<>();
            this.hostilityMgr.getRecords().forEach(record -> {
                final Player player = record.agent.getOriginPlayer();
                if(player != null && player.isActive() && player.isTaskMonsters(monster.getMonsterId())) {
                    attackRoles.add(player.getRoleid());
                }
            });
            if (!attackRoles.isEmpty()) {
                MKillTaskMonster msg = new MKillTaskMonster();
                msg.roles.addAll(attackRoles);
                msg.monsterid = monster.getMonsterId();
                map.sendXdbServer(msg);
            }
        }
        //retainListeners(new HashSet<>(Arrays.asList(Listener.DEATH, Listener.REVIVE, Listener.LEAVE)));
		addDeferTask(() -> broadcastToNearby(new map.msg.SDead(attacker.getAid())));
	}

	public final void reviveAtCurPosition() {
		revive(null, null);
	}

	public final void revive(pathfinding.Vector3 position, pathfinding.Vector3 orient) {
		setRevive();
		clearDead();
		onRevive(position, orient);
		afterRevive();
		clearRevive();
		broadcastToNearby(new SRevive());
	}

	protected void onRevive(pathfinding.Vector3 position, pathfinding.Vector3 orient) {
		moveMgr.onRevive(position, orient);
		slotMgr.onRevive();
		attrMgr.onRevive();
		buffMgr.onRevive();
		skillMgr.onRevive();
        hostilityMgr.onRevive();
	}

	protected void afterRevive() {
		trigger(Listener.REVIVE, this);
		broadcastToNearby(createSelfEnter());
	}

	protected void fillFighterCommon(FighterCommon fc) {
		fc.isdead = isDead() ? 1 : 0;
		fc.isborn = isborn ? 1 : 0;
		fc.isrevive = isrevive ? 1 : 0;
		fc.camp = camp;
		fc.position = MapUtils.p2m(position);
		fc.orient = MapUtils.p2m(orient);
		fc.targetposition = MapUtils.p2m(moveMgr.getTargetPosition() != null ? moveMgr.getTargetPosition() : position);
        skillMgr.fillSkills(fc.skills);
		attrMgr.fillAttrs(fc.attrs);
	}
	
	@Override
	public void onLeave() {
		super.onLeave();
		slotMgr.onLeave();
        clearListeners();
	}


	protected void updateWhileAlive(long now) {
		stateMgr.update(now);
        moveMgr.update(now);
		skillMgr.update(now);
		buffMgr.update(now);
        hostilityMgr.update(now);
	}

	protected void updateWhileDead(long now) {

	}

	@Override
	public void update(long now) {
		if(!isDead()) {
			updateWhileAlive(now);
		} else {
			updateWhileDead(now);
		}
	}

	public ErrorCode performSkillAndNotify(Skill.Builder param) {
		final SSkillPerform re = new SSkillPerform();
		final Fighter defencer = param.defencer;
		re.skillid = param.skillid;
		re.attackerid = getAid();
		re.targetid = defencer != null ? defencer.getAid() : 0;

        if(param.direction.getXZMagnitude() < 0.001) {
            param.direction = pathfinding.Vector3.FORWARD;
        }
		re.direction = MapUtils.p2m(param.direction);
        setOrient(param.direction);
		final ErrorCode err = skillMgr.performSkill(param);
		re.retcode = err.getErrorId();
		if(err.ok()) {
            re.mp = (int)attrMgr.getMPValue();
            broadcastToNearby(re);
		} else {
            final Player player = getOriginPlayer();
			if(player != null && player.isPlayer()) {
                player.sendNotRoleMsg(re);
            }
		}
		return err;
	}


	public void process(XEvolveSkill msg) {
		this.skillMgr.evolve(msg.oldskillid, msg.newskillid, msg.level);
	}

	public void process(XUpgradeSkill msg) {
		this.skillMgr.upgrade(msg.skillid, msg.level);
	}

	public void process(XNewSkill msg) {
		this.skillMgr.upgrade(msg.skillid, msg.level);
	}
}
