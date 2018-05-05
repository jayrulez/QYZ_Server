package map.skill;

import cfg.CfgMgr;
import cfg.fight.Relation;
import cfg.fight.StateType;
import cfg.skill.*;
import map.agent.Agent;
import map.agent.Fighter;
import map.agent.StateMgr;
import map.buff.effect.InternEffcteIds;
import map.buff.effect.SkillEffect;
import map.map.GameMap;
import pathfinding.MathUtil;
import pathfinding.Vector3;

import java.util.*;

import static cfg.Const.NULL;

public final class Skill {
	public final static class Builder {
		public int skillid;
		public Fighter defencer;
		public Vector3 direction;
	}

	private final cfg.skill.SkillAction skillCfg;
	private final cfg.skill.Skilldmg dmgCfg;

	private final int skillLevel;

	private final Fighter attacker;
	private final Fighter defencer;
	private final Vector3 direction;

    private final double modelScale;

    private final List<SkillEffect> effects;

	private final StateMgr asm;

	private final LinkedList<ActionHandler> unactiveHandlers = new LinkedList<>();
	private final LinkedList<ActionHandler> activeHandlers = new LinkedList<>();

	private Fighter activeTarget; // 技能释放时指定的目标,第一优先目标

	private long startTime;
	private long endTime;

	private boolean interrupted;

	public Skill(Skilldmg dcfg, SkillAction scfg, int skillLevel, double modelScale, int endAttackTime, List<SkillEffect> effects, Fighter attacker, Fighter defencer, Vector3 direction) {
		this.skillCfg = scfg;
		this.dmgCfg = dcfg;
        this.modelScale = modelScale;
		this.skillLevel = skillLevel;
		this.attacker = attacker;
		this.defencer = defencer;
		this.direction = direction;
		this.asm = attacker.getStateMgr();
        this.effects = effects;

		this.activeTarget = defencer;
		this.startTime = attacker.getNow();
		this.endTime = startTime + endAttackTime;
		if(!dcfg.isnormal) {
			asm.setState(InternEffcteIds.CANNOT_SKILL, StateType.CANNOT_SKILL, endAttackTime - 100);
		}

		if(!scfg.canmove) {
			if(scfg.startmovetime > 0)
				asm.setState(InternEffcteIds.CANNOT_MOVE_IN_SKILL, StateType.CANNOT_MOVE, (long)(scfg.startmovetime * 1000) - 100);

			if(scfg.endmovetime > 0) {
				final long endCanMoveTime = (long)(scfg.endmovetime * 1000);
                final long cannotMoveTime = endAttackTime - endCanMoveTime - 100;
                if(cannotMoveTime > 0) {
                    attacker.schedule(() -> {
                        if (!interrupted)
                            asm.setState(InternEffcteIds.CANNOT_MOVE_IN_SKILL, StateType.CANNOT_MOVE, cannotMoveTime);
                    }, endCanMoveTime);
                }
			}
		}
		interrupted = false;
        for(cfg.skill.Action action :  scfg.actions) {
            final ActionHandler ah = create(action);
            if(ah != null)
                unactiveHandlers.add(ah);
        }
	}

    public final List<SkillEffect> getEffects() {
        return effects;
    }

	public final cfg.skill.SkillAction getSkillCfg() {
		return skillCfg;
	}

	public final cfg.skill.Skilldmg getDmgCfg() {
		return dmgCfg;
	}

    public final double getModelScale() {
        return modelScale;
    }

	public final int getSkillid() {
		return dmgCfg.id;
	}

	public final Fighter getAttacker() {
		return attacker;
	}

	public final Fighter getDefencer() {
		return defencer;
	}

	public final Vector3 getDirection() {
		return skillCfg.canrotate ? attacker.getOrient() : direction;
	}

	public final int getSkillLevel() {
		return skillLevel;
	}

	public final long getEndTime() {
		return this.endTime;
	}

	public final boolean isEnd(long now) {
		return this.endTime <= now;
	}

	public final boolean isEnd() {
		return isEnd(this.attacker.getNow());
	}

	public void update(long now) {
		for(Iterator<ActionHandler> it = unactiveHandlers.iterator() ; it.hasNext() ; ) {
			final ActionHandler action = it.next();
			if(action.startTime <= now) {
				activeHandlers.add(action);
				it.remove();
			}
		}

		final ArrayList<ActionHandler> toRemoves = new ArrayList<>();
		for(ActionHandler action : activeHandlers) {
			if(!action.process(now)) {
				toRemoves.add(action);
			}
		}
		activeHandlers.removeAll(toRemoves);
	}

	public void interrupt(long now) {
		interrupted = true;
		asm.clearState(InternEffcteIds.CANNOT_MOVE_IN_SKILL);
		asm.clearState(InternEffcteIds.CANNOT_SKILL);
	}

	private int hitPointOffset = 0;
	ActionHandler create(cfg.skill.Action action) {
		final long beginTime = startTime + (long)(action.timeline * 1000);
		switch (action.getTypeId()) {
			case Attack.TYPEID: {
				return HitPointHandler.create(this, beginTime, (cfg.skill.Attack)action);
			}
			case Movement.TYPEID: {
				return new MovementHandler(this, beginTime, (cfg.skill.Movement)action);
			}
            case FlyWeapon.TYPEID: {
                return new SpwanObjectHandler(this, beginTime, (cfg.skill.SpawnObject)action, 0);
            }
            case Bomb.TYPEID: {
                final int oldOffset = hitPointOffset;
                hitPointOffset += skillCfg.attacklists_id.get(((Bomb)action).attacklistid).attacks.size();
                return new SpwanObjectHandler(this, beginTime, (cfg.skill.SpawnObject)action, oldOffset);
            }
            default:
				return null;
		}
	}

	public List<Fighter> getTargets(int relation, cfg.skill.HitZone hitZone, int extraNum) {
		final List<Fighter> targets = new ArrayList<>();
		Vector3 basePosition;
		final int maxtarget = (hitZone.maxtarget <= 0 ? 10 : hitZone.maxtarget) + extraNum;
		final Vector3 attackPosition = attacker.getPosition();
        final GameMap gameMap= attacker.getMap();
		if(skillCfg.needtarget) {
//            Fighter target = activeTarget;
//			if(maxtarget == 1) {
//				if(target.isActive() && !target.isDead()
//                        && attackPosition.getSubXZMagnitude(target.getPosition()) <= skillCfg.attackrange * modelScale + target.getBodyRadius()
//                        && gameMap.checkTargetType(attacker, target, relation))
//					targets.add(target);
//                return targets;
//			}
			basePosition = skillCfg.relatetype == cfg.skill.SkillAction.RELATE_SELF ? attackPosition : activeTarget.getPosition();
		} else {
			basePosition = attackPosition;
		}
        gameMap.foreachNearbyInAgentMap(attacker,
				calcRadius(hitZone) + attacker.getBodyRadius() + attacker.getMap().getMaxDefencerBodyRadius(),
				agent -> {
					if(agent.isActive() && (agent instanceof Fighter)) {
						Fighter target = (Fighter)agent;
						if(!target.isDead() && target.canBeattacked() && gameMap.checkTargetType(attacker, target, relation) && inHitZone(hitZone, modelScale, basePosition, getDirection(), target)) {
						    if(targets.size() >= maxtarget) {
						        if(target == activeTarget) {
						            targets.set(targets.size() -1, target);
                                }
                            } else {
                                targets.add(target);
                            }
						}
					}
				});
		return targets;
	}

	public Fighter getActiveTarget() {
		return this.activeTarget;
	}

    public static boolean isActive(int skillid) {
        return CfgMgr.skilldmg.containsKey(skillid);
    }

    public static cfg.skill.SkillAction getSkillAction(cfg.skill.ModelActions mcfg, String actionName) {
        final cfg.skill.SkillAction action = mcfg.skillactions.get(actionName);
        if(action != null)
            return action;
        return !mcfg.basemodelname.isEmpty() ? getSkillAction(CfgMgr.modelactions.get(mcfg.basemodelname), actionName) : null;
    }

    public static int getBaseEvolveSkillid(int skillid) {
        return map.MapModule.skillid2BaseEvolveSkillid.getOrDefault(skillid, 0);
    }

    public static boolean inCylinder(cfg.skill.HitZone hitzone, double modelScale, Vector3 basePosition, Vector3 direction, Agent target) {
        final Vector3 targetPosition = target.getPosition();
        Vector3 center = basePosition.plus(direction.scale(hitzone.zoffset * modelScale));
        Vector3 c2t = targetPosition.sub(center);
        final double c2tLength = c2t.getXZMagnitude();
        final double bodyRadius = target.getBodyRadius();
        if(c2tLength <= bodyRadius)
            return true;
        final double attackRadius = hitzone.xlength * modelScale/ 2;
        if(c2tLength > attackRadius + target.getBodyRadius())
            return false;
        double radianBetweenDirectionAndTarget = c2t.radianXZBetween(direction);
        double yradian = Math.toRadians(hitzone.yangle/2);
        if(yradian >= radianBetweenDirectionAndTarget)
            return true;
        // 两个圆相交的角度的一半
        double radianCross = MathUtil.radianOfTriangle(attackRadius, c2tLength, bodyRadius);
        // 角度与半径判定
        return yradian + radianCross >= radianBetweenDirectionAndTarget;
    }

    public static boolean inRectangle(cfg.skill.HitZone hitzone, double modelScale, Vector3 basePosition, Vector3 direction, Agent target) {
        final double xRadius = hitzone.xlength * modelScale / 2;
        final double zRadius = hitzone.zlength * modelScale / 2;
        Vector3 center = basePosition.plus(direction.scale(hitzone.zoffset * modelScale));
        final double bodyRadius = target.getBodyRadius();
        final Vector3 targetPosition = target.getPosition();
        Vector3 c2t = targetPosition.sub(center);
        final double c2tLength = c2t.getXZMagnitude();
        if(c2tLength <= bodyRadius)
            return true;

        // 把direction旋转为x轴后,c2t的坐标位置.
        Vector3 rotatec2t = c2t.rotateXZ(direction);
        final double absx = Math.abs(rotatec2t.x);
        final double absz = Math.abs(rotatec2t.z);
        //如果target的包围方盒不与hitzone相交,则必不在hitzone内
        if(absx > xRadius + bodyRadius
                || absz > zRadius + bodyRadius)
            return false;

        return absx <= xRadius || absz <= zRadius
                || (absx - xRadius) * (absx - xRadius) + (absz - zRadius) * (absz - zRadius) <= bodyRadius * bodyRadius;
    }

    public static boolean inTriangle(cfg.skill.HitZone hitzone, double modelScale, Vector3 basePosition, Vector3 direction, Agent target) {
        assert(hitzone.yangle < 180);
        final Vector3 targetPosition = target.getPosition();
        Vector3 center = basePosition.plus(direction.scale(hitzone.zoffset * modelScale));
        Vector3 c2t = targetPosition.sub(center);
        final double c2tLength = c2t.getXZMagnitude();
        final double bodyRadius = target.getBodyRadius();
        if(c2tLength <= bodyRadius)
            return true;
        final double xRadius = hitzone.xlength * modelScale / 2;
        if(c2tLength > xRadius + target.getBodyRadius())
            return false;


        double radianBetweenDirectionAndTarget = c2t.radianXZBetween(direction);
        double yradian = Math.toRadians(hitzone.yangle/2);
        final double targetRadian = Math.asin(bodyRadius/c2tLength);
        if(yradian + targetRadian < radianBetweenDirectionAndTarget)
            return false;
        final Vector3 rotatec2t = c2t.rotateXZ(direction);
        if(yradian >= targetRadian + radianBetweenDirectionAndTarget) {
            return rotatec2t.x <= hitzone.zlength * modelScale + bodyRadius;
        } else {
            final double deltaRadian = Math.abs(yradian - radianBetweenDirectionAndTarget);
            final double aSinRadian = c2tLength * Math.sin(deltaRadian);
            return c2tLength * Math.cos(deltaRadian) - Math.sqrt(bodyRadius * bodyRadius - aSinRadian * aSinRadian)
                    < xRadius / Math.sin(yradian);
        }
    }

    public static boolean inHitZone(cfg.skill.HitZone hitzone, double modelScale, Vector3 basePosition, Vector3 direction, Agent target) {
//		final Vector2 targetPosition = target.getPosition();
//		if(targetPosition.z + target.getBodyHeight() < basePosition.z + hitzone.bottomheight
//			|| targetPosition.z > basePosition.z + hitzone.topheight)
//			return false;
        switch(hitzone.shape) {
            case cfg.skill.HitZone.CYLINDER: {
                return inCylinder(hitzone, modelScale, basePosition, direction, target);
            }
            case cfg.skill.HitZone.RECT: {
                return inRectangle(hitzone, modelScale, basePosition, direction, target);
            }
            case cfg.skill.HitZone.TRIANGLE: {
                return inTriangle(hitzone, modelScale, basePosition, direction, target);
            }
            default : {
                throw new RuntimeException("unknown hitzone.shape:" + hitzone.shape);
            }
        }
    }

    // 注意 isFriend 并不等于 !isEnemy
//    public static boolean isFriend(Fighter attacker, Fighter defencer) {
//        return !isEnemy(attacker, defencer);
//    }

//    public static boolean isEnemy(Fighter attacker, Fighter defencer) {
//        if(MapModule.campRelations[attacker.getCamp()][defencer.getCamp()] == Relation.ENEMY)
//            return true;
//
//        final Player pa = attacker.getOriginPlayer();
//        if(pa == null)
//            return false;
//        final Player pd = defencer.getOriginPlayer();
//        if(pd == null)
//            return false;
//        if(pa == pd)
//            return false;
//
//        if(defencer.getLevel() < CfgMgr.roleconfig.pkinfo.level.level)
//            return false;
//
//        switch (pa.getPkstate()) {
//            case PKState.FAMILY_AND_TEAM:
//                return !pa.isSameFamily(pd) && !pa.isSameTeam(pd);
//            case PKState.TEAM:
//                return !pa.isSameTeam(pd);
//            default:
//                return false;
//        }
//    }
//
//    public static boolean isFriend(Fighter attacker, Fighter defencer) {
//        final Fighter ao = attacker.getOwner();
//        final Fighter co = defencer.getOwner();
//        if(ao == co) return true;
//
//        final Player pa = ao.asPlayerOrFakePlayer();
//        if(pa == null)
//            return false;
//        final Player pd = defencer.getOriginPlayer();
//        if(pd == null)
//            return false;
//        if(pa == pd)
//            return true;
//
//    }

    public static double calcRadius(cfg.skill.HitZone hitzone) {
        switch(hitzone.shape) {
            case cfg.skill.HitZone.CYLINDER: {
                return Math.abs(hitzone.zoffset) + hitzone.xlength/2;
            }
            case cfg.skill.HitZone.RECT: {
                return Math.max(Math.abs(hitzone.zoffset) + hitzone.zlength/2, hitzone.xlength/2);
            }
            case cfg.skill.HitZone.TRIANGLE: {
                return Math.max(Math.abs(hitzone.zoffset) + hitzone.zlength/2 + hitzone.xlength/2, hitzone.xlength/2);
            }
            default: {
                throw new RuntimeException("unknown shape:" + hitzone.shape);
            }
        }
    }
}
