package map.ai;

import cfg.CfgMgr;
import cfg.ai.DefaultAI;
import cfg.ai.HostilityType;
import cfg.fight.AgentType;
import cfg.fight.AttrId;
import cfg.map.PatrolType;
import map.MapUtils;
import map.agent.*;
import map.buff.effect.AttrEffect;
import map.buff.effect.Effect;
import map.buff.effect.InternEffcteIds;
import map.map.GameMap;
import map.skill.Skill;
import pathfinding.MathUtil;
import pathfinding.Vector3;

import java.util.Collections;
import java.util.List;

/**
 * Created by huangqiang on 2016/2/22.
 */
public class MonsterAI extends AI {

    private final boolean proactive;
    private final float guardRadius;
    private final float traceRadius;
    private final int hostilitytype;
    private final int walkbackhealhprate;
    private final float walkbackhealhppercentrate;
    private final boolean walkback;

    private List<Vector3> patrolPath;
    private final int patrolType;

    private final MoveMgr moveMgr;
    private final SkillMgr skillMgr;
    private final NearbySlotMgr slotMgr;
    private final AttrMgr attrMgr;
    private final BuffMgr buffMgr;
    private final HostilityMgr hostMgr;

    private  final cfg.monster.Monster mcfg;


    public MonsterAI(Fighter self, AIFactory.Builder b) {
        super(self);
        this.walkback = ((DefaultAI)b.acfg).walkback;
        this.proactive = b.proactive;
        this.guardRadius = b.guardRadius;
        this.traceRadius = b.traceRadius;
        this.hostilitytype = b.hostilitytype;
        this.walkbackhealhppercentrate = b.walkbackhealhppercentrate;
        this.walkbackhealhprate = b.walkbackhealhprate;
        this.patrolType = b.patrolType;
        this.patrolPath = b.patrolPath;
        this.mcfg = CfgMgr.monster.get(((Monster)self).getMonsterId());

        this.moveMgr = self.getMoveMgr();
        this.skillMgr = self.getSkillMgr();
        this.slotMgr = self.getSlotMgr();
        this.attrMgr = self.getAttrMgr();
        this.buffMgr = self.getBuffMgr();
        this.hostMgr = self.getHostilityMgr();

        pathIdx = 0;
        originPos = self.getPosition();
        this.patrolSpeedEffect = new AttrEffect(InternEffcteIds.PATROL, self, AttrId.MOVE_SPEED, mcfg.patrolspeed - attrMgr.getRawMoveSpeed());

        this.installListener = false;
        this.onBeAttack = this::onBeAttackFunc;

        //this.self.addListener(Listener.BE_DAMAGE, onBeAttack);

        switchToAction(IDLE);
    }

    private final Action IDLE = this::doIdle;
    private final Action PATROL = this::doPatrol;
    private final Action WALK_TO_TARGET = this::doWalkToTarget;
    private final Action ATTACK = this::doAttack;
    private final Action WALK_BACK = this::doWalkBack;

    private boolean installListener;
    private final Listener onBeAttack;

    private Effect patrolSpeedEffect;

    private int pathIdx;
    private Fighter curTarget;
    private double relateTargetDistance;
    private Vector3 relateTargetPosition;
    private Vector3 originPos;
    private long nextChooseTargetTime;
    private Vector3 patrolTargetPosition;

    private final static int CHOOST_TARGET_INTERVAL = 2000;

    private void onBeAttackFunc(Agent go, int evtid, Object param) {
        setNextUpdateAfter(0);
    }

    private boolean doIdle(long now) {
        if(!proactive && !installListener) {
            self.addListener(Listener.BE_DAMAGE, onBeAttack);
            installListener = true;
        }
        final Fighter target = getTarget();
        if (target == null) {
            if (patrolType != PatrolType.NULL && patrolPath != null) {
                switchToAction(PATROL);
                curTarget = null;
                buffMgr.addEffect(this.patrolSpeedEffect);
                patrolTargetPosition = null;
                //Trace.info("DefaultAI. monster:{} patrol", self);
                return true;
            } else {
                setNextUpdateAfter(proactive ? 2000 : 1000000);
            }
        } else {
            buffMgr.removeEffect(this.patrolSpeedEffect);
            originPos = self.getPosition();
            onWalkToTarget(target);
            return true;
        }
        return false;
    }

    private boolean doPatrol(long now) {
        final Fighter target = getTarget();
        if (target != null) {
            buffMgr.removeEffect(this.patrolSpeedEffect);
            originPos = self.getPosition();
            onWalkToTarget(target);
            return true;
        } else {
            if(patrolTargetPosition == null || reach(self.getPosition(), patrolTargetPosition)) {
                if (patrolType == PatrolType.RANDOM) {
                    patrolTargetPosition = MapUtils.randomPolygonsPosition(patrolPath);
                } else {
                    if(patrolTargetPosition != null) {
                        pathIdx++;
                    }
                    if (pathIdx >= patrolPath.size()) {
                        switch (patrolType) {
                            case PatrolType.ONCE: {
                                switchToAction(IDLE);
                                return false;
                            }
                            case PatrolType.ROUND: {
                                Collections.reverse(patrolPath);
                                pathIdx = 1;
                                break;
                            }
                            case PatrolType.CYCLE: {
                                pathIdx = 0;
                                break;
                            }
                            case PatrolType.REPEAT: {
                                moveMgr.moveTo(patrolPath.get(0));
                                pathIdx = 1;
                                break;
                            }
                            default: {
                                throw new RuntimeException("unknown patroltype:" + patrolType);
                            }
                        }
                    }
                    patrolTargetPosition = patrolPath.get(pathIdx);
                }
            }
            moveMgr.setTarget(patrolTargetPosition);
            setNextUpdateAfter(proactive ? 1500 : (long)(patrolTargetPosition.getSubXZMagnitude(self.getPosition()) / attrMgr.getMoveSpeed() * 1000) + 200);
        }
        return false;
    }

    private boolean doAttack(long now) {
//        if(!skillMgr.isCurSkillFinished()) {
//            //setNextUpdateAfter(3000);
//            return false;
//        }
        final Vector3 curPos = self.getPosition();
        if(!curTarget.isActive() || curTarget.isDead() || needChooseNewTarget(now)) {
            final Fighter target = getTarget();
            if (target != null) {
                if(target != curTarget) {
                    onWalkToTarget(target);
                    return true;
                } else {
                    // continue attack.
                }
            } else {
                onWalkBack();
                return false;
            }
        }

        if(curPos.getSubXZMagnitude(curTarget.getPosition()) > skillMgr.getMaxAvailableSkillDistance()) {
            onWalkToTarget(curTarget);
            return true;
        }

        if(!self.canSkillattack() || !curTarget.canBeattacked())
            return false;

        final Vector3 targetPos = curTarget.getPosition();
        final Vector3 direction = targetPos.sub(curPos);
        final double curDistance = direction.getXZMagnitude();
        final int skillid = skillMgr.getBestActiveSkill(curTarget, curDistance);

        if(skillid > 0) {
            final Skill.Builder param = new Skill.Builder();
            param.skillid = skillid;
            param.defencer = curTarget;
            param.direction = direction;

            if(self.performSkillAndNotify(param).ok()) {
                setNextUpdateAfter(skillMgr.getCurSkillDuration() + 200);
            } else {
                setNextUpdateAfter(2000);
            }
        }
        return false;
    }

    private long nextSetTargetTime;
    private long nextCheckTraceRadiusTime;
    private boolean doWalkToTarget(long now) {
        final Vector3 selfPos = self.getPosition();
        final Vector3 targetPos = curTarget.getPosition();
        final double moveToDistance = relateTargetDistance + 0.01;
        final Vector3 subPos = targetPos.sub(selfPos);
        if(subPos.getXZSquare() < moveToDistance * moveToDistance) {
            moveMgr.stopAtCurPosition();
            switchToAction(ATTACK);
            return true;
        }
//        final Vector3 curTargetVelocity = curTarget.getMoveMgr().getVelocity();
//        if(moveMgr.isStop() //|| !lastTargetVelocity.isSubXZMagnitudeInRadius(curTargetVelocity, 0.01)) {
//                || MathUtil.vector2VectorXZSquareDistance(subPos, moveMgr.getVelocity().sub(curTargetVelocity))  > relateTargetDistance * relateTargetDistance) {
//            calcVelocityAndSetTarget(curTargetVelocity);
//        }

        if(now >= nextCheckTraceRadiusTime) {
            nextCheckTraceRadiusTime = now + 1000;
            if(!selfPos.isSubXZMagnitudeInRadius(originPos, traceRadius)) {
                onWalkBack();
                return false;
            }
        }

        if(now >= nextSetTargetTime) {
            final Vector3 newTargetPos = targetPos.plus(relateTargetPosition);
            moveMgr.setTarget(newTargetPos);
            nextSetTargetTime = now + 1500;//Math.max(1500, (long)(selfPos.getSubXZMagnitude(newTargetPos) * 1000 / self.getSpeed()));
//
//            final Vector3 u = curTarget.getMoveMgr().getVelocity();
//            final Vector3 v = newTargetPos.sub(curPos);
//
//            final double selfSpeed = self.getSpeed();
//            final double a = v.getXZSquare();
//            final double b = u.dotXZ(v);
//            final double c = u.getXZSquare() - selfSpeed * selfSpeed;
//            final double k = (-b + Math.sqrt(Math.max(b*b - a*c, 0))) / a;
//            final Vector3 w;
//            if(k > 0) {
//                w = u.plus(v.multi(k));
//            } else {
//                w = v;
//            }
//            moveMgr.setTarget(curPos.plus(k > 0 ? w.multi(1/k) : v.scaleXZ(selfSpeed * 5000)));
        }

//        final Vector3 curTargetVelocity = curTarget.getMoveMgr().getVelocity();
//        final Vector3 subVolocity = moveMgr.getVelocity().sub(curTargetVelocity);
//        if(subPos.dotXZ(subVolocity) <= 0 || MathUtil.vector2VectorXZSquareDistance(subPos, subVolocity) > relateTargetDistance * relateTargetDistance) {
//            if(slotMgr.getSlot() == null) {
//                calcSlotAndRelatePosition();
//            }
////            final Vector3 moveTo = targetPos.plus(relateTargetPosition);
////            moveMgr.setTarget(moveTo);
//            calcVelocityAndSetTarget(curTargetVelocity);
//        }


//        final Vector3 oldTargetPos = moveMgr.getTargetPosition();
//        if(oldTargetPos == null || subPos.dotXZ(oldTargetPos.sub(curPos)) <= 0 || MathUtil.vertex2lineXZSquareDistance(curPos, oldTargetPos, targetPos) > moveToDistance * moveToDistance) {
//            if(slotMgr.getSlot() == null) {
//                calcSlotAndRelatePosition();
//            }
//            Vector3 relateSelfPos = targetPos.plus(relateTargetPosition).sub(curPos);
////            if(relateSelfPos.getXZSquare() < 10 * 10) {
////                relateSelfPos = relateSelfPos.scaleXZ(10);
////            }
//            final Vector3 moveTo = targetPos.plus(relateSelfPos);
//            moveMgr.setTarget(moveTo);
//        }
//        setNextUpdateAfter(Math.min((long)(subPos.getXZSquare() * 1000 / (self.getSpeed() + curTarget.getSpeed())), 1000L));
        return false;
    }

    private boolean doWalkBack(long now) {
        if(self.getPosition().isSubXZMagnitudeInRadius(originPos, 0.1)) {
            attrMgr.resetHPMP();
            switchToAction(IDLE);
            return false;
        }
//        final AttrMgr am = self.getAttrMgr();
//        if(walkbackhealhprate > 0) {
//            am.addHPValue((int)(walkbackhealhprate * self.getDeltaTime() / 1000));
//        }
//        if(walkbackhealhppercentrate > 0) {
//            am.addHPValue((int)(walkbackhealhppercentrate * am.getHPFullValue() * self.getDeltaTime() / 1000));
//        }
        moveMgr.setTarget(originPos);
        setNextUpdateAfter(3000);
        return false;
    }

    private Vector3 getBasePosition() {
        return inAction(PATROL) ? self.getPosition() : originPos;
    }

    private Fighter getMostHostyTarget() {
        if(hostMgr.isEmpty()) return null;
        HostilityMgr.Record mostHostyRecord = null;
        final Vector3 basePosition = getBasePosition();
        final GameMap gameMap = self.getMap();
        for(HostilityMgr.Record record : hostMgr.getRecords()) {
            Fighter fighter = record.agent;
            if(fighter.isActive() && !fighter.isDead() && fighter.canBeattacked() && basePosition.getSubXZMagnitude(fighter.getPosition()) < traceRadius && gameMap.isEnemy(self, fighter)
                    && (mostHostyRecord == null || record.totalValue > mostHostyRecord.totalValue)) {
                mostHostyRecord = record;
            }
        }
        return mostHostyRecord != null ? mostHostyRecord.agent : null;
    }


    Fighter nearestTarget;
    double minDistance;
    private Fighter getNearestTarget() {
        nearestTarget = null;
        minDistance = 1.0e7;
        self.getMap().getAgentMap().foreachNearbyAgents(self, AgentType.PLAYER | AgentType.MONSTER | AgentType.PET, guardRadius, this::chooseNearest);
        return nearestTarget;
    }

    private void chooseNearest(Fighter target) {
        if(target.isActive() && !target.isDead() && target.canBeattacked() && getBasePosition().getSubXZMagnitude(target.getPosition()) < traceRadius &&  self.getMap().isEnemy(self, target)) {
            final double distance = self.getPosition().getSubXZMagnitude(target.getPosition());
            if(distance < minDistance) {
                nearestTarget = target;
                minDistance = distance;
            }
        }
    }

    Fighter getTarget() {
        nextChooseTargetTime = self.getNow() + CHOOST_TARGET_INTERVAL;
        Fighter target = getMostHostyTarget();
        if(target == null && proactive) {
            target = getNearestTarget();
        }
        return target;
    }

    private boolean needChooseNewTarget(long now) {
        return hostilitytype == HostilityType.DYNAMIC && now >= nextChooseTargetTime;
    }

    private void calcSlotAndRelatePosition() {
        slotMgr.clearSlot();
        relateTargetDistance = skillMgr.getMaxAvailableSkillDistance() - 0.1;
        NearbySlotMgr.Slot curTargetSlot = curTarget.getSlotMgr().alloc(self, relateTargetDistance);
        slotMgr.setSlot(curTargetSlot);
        relateTargetPosition = new Vector3(Math.cos(curTargetSlot.radian), 0, Math.sin(curTargetSlot.radian)).scale(relateTargetDistance);
    }

//    private void calcVelocityAndSetTarget(Vector3 curTargetVelocity) {
//        final Vector3 curPos = self.getPosition();
//        final Vector3 u = curTargetVelocity;
//        final Vector3 v = curTarget.getPosition().plus(relateTargetPosition).sub(curPos);
//
//        final double selfSpeed = self.getSpeed();
//        final double a = v.getXZSquare();
//        final double b = u.dotXZ(v);
//        final double c = u.getXZSquare() - selfSpeed * selfSpeed;
//        final double k = (-b + Math.sqrt(Math.max(b*b - a*c, 0))) / a;
//        final Vector3 w;
//        if(k > 0) {
//            w = u.plus(v.multi(k));
//        } else {
//            w = v;
//        }
//        moveMgr.setTarget(curPos.plus(k > 0 ? w.multi(1/k) : v.scaleXZ(selfSpeed * 5000)));
//    }

    private void onWalkToTarget(Fighter target) {
        if(installListener) {
            self.removeListener(Listener.BE_DAMAGE, this.onBeAttack);
            installListener = false;
        }
        switchToAction(WALK_TO_TARGET);
        curTarget = target;
        calcSlotAndRelatePosition();
        final Vector3 newTargetPos = curTarget.getPosition().plus(relateTargetPosition);
        moveMgr.setTarget(newTargetPos);
        //Trace.info("DefaultAI. monster:{} walk to target:{}", self, target);
    }

    private void onWalkBack() {
        slotMgr.clearSlot();
        curTarget = null;
        if(this.walkback) {
            switchToAction(WALK_BACK);
            moveMgr.setTarget(originPos);
        } else {
            switchToAction(IDLE);
            moveMgr.stopAtCurPosition();
        }
        //Trace.info("DefaultAI. monster:{} walkback", self);
    }

    private boolean reach(Vector3 curPos, Vector3 targetPos) {
        return curPos.getSubXZSquare(targetPos) < 1e-4;
    }
}
