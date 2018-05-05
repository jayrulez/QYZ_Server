package map.ai;

import cfg.CfgMgr;
import cfg.fight.AttrId;
import map.agent.*;
import map.skill.Skill;
import pathfinding.Vector3;

/**
 * Created by huangqiang on 2016/3/4.
 */
public class ArenaPetAI extends AI {
    private final Player owner;
    private final APet self;
    private final float guardRadius;
    private final float followNearRadius;
    private final float followFarRadius;
    private final float rawSpeed;
    private final MoveMgr moveMgr;
    private final SkillMgr skillMgr;

    public ArenaPetAI(APet self) {
        super(self);
        this.owner = self.getOwner();
        this.self = self;

        final cfg.pet.PetConfig pcfg = CfgMgr.petconfig;
        this.followNearRadius = pcfg.follownearradius;
        this.followFarRadius = pcfg.followfarradius;
        this.guardRadius = pcfg.guardradius;

        this.moveMgr = self.getMoveMgr();
        this.skillMgr = self.getSkillMgr();
        this.rawSpeed = self.getAttrMgr().getRawMoveSpeed();


        curTarget = null;
        curSkill = null;
        switchToAction(IDLE);
    }

    private final Action IDLE = this::doIdle;
    private final Action WALK_TO_TARGET = this::doWalkToTarget;
    private final Action ATTACK = this::doAttack;
    private final Action FOLLOW = this::doFollow;

    private boolean doIdle(long now) {
        if (checkFarFromOwnerOrNotInFight()/* || self.isBeingAttack() */)
            return false;
        final TargetInfo target = getTarget();
        if (target != null) {
            curSkill = target.skill;
            onWalkToTarget(target.target);
            return true;
        }
        return false;
    }

    private boolean doWalkToTarget(long now) {
        if (checkFarFromOwnerOrNotInFight() || checkTargetInvalid())
            return true;

        final Vector3 curPos = self.getPosition();
        final Vector3 targetPos = curTarget.getPosition();
        if (curPos.getSubXZMagnitude(targetPos) <= curSkill.skillAction.attackrange) {
            moveMgr.stopAtCurPosition();
            switchToAction(ATTACK);
            return true;
        }
        if (self.canMove())
            moveMgr.setTarget(targetPos);
        return false;
    }

    private boolean doAttack(long now) {
        if (!skillMgr.isCurSkillFinished() || checkFarFromOwnerOrNotInFight() || checkTargetInvalid())
            return false;

        final Vector3 curPos = self.getPosition();
        final Vector3 targetPos = curTarget.getPosition();
        if (curPos.getSubXZMagnitude(targetPos) > curSkill.skillAction.attackrange) {
            onWalkToTarget(curTarget);
            return true;
        }

        if (curSkill.activeTime > self.getNow()
                || (curSkill.skilldmg.isnormal && !self.canNormalattack())
                || (!curSkill.skilldmg.isnormal && !self.canSkillattack()))
            return false;

        final Vector3 direction = targetPos.sub(curPos);
        final int skillid = curSkill.skilldmg.id;

        final Skill.Builder param = new Skill.Builder();
        param.skillid = skillid;
        param.defencer = curTarget;
        param.direction = direction;

        self.performSkillAndNotify(param);

        curTarget = null;
        curSkill = null;
        return false;
    }

    private boolean doFollow(long now) {
        final Vector3 curPos = self.getPosition();
        final Vector3 tarPos = getDefaultPositionByTargetPosition();
        if (curPos.getSubXZMagnitude(tarPos) < 0.5  || curPos.isSubXZMagnitudeInRadius(owner.getPosition(), self.getDefaultRelateRadius() + 0.5)) {
            self.getAttrMgr().setRawAttr(AttrId.MOVE_SPEED, rawSpeed);
            moveMgr.stopAtCurPosition();
            switchToAction(IDLE);
            return false;
        } else if(!curPos.isSubXZMagnitudeInRadius(owner.getPosition(), followFarRadius)) {
            moveMgr.stopAtPosition(getDefaultPositionByOwnerPosition(), owner.getOrient());
            switchToAction(IDLE);
            return false;
        }
        if (self.canMove()) {
            self.getAttrMgr().setRawAttr(AttrId.MOVE_SPEED, owner.getAttrMgr().getMoveSpeed());
            moveMgr.setTarget(tarPos);
        }
        return false;
    }

    private Fighter curTarget = null;
    private SkillMgr.SkillData curSkill = null;

    private void onWalkToTarget(Fighter target) {
        switchToAction(WALK_TO_TARGET);
        curTarget = target;
    }

    private void onFollow() {
        switchToAction(FOLLOW);
        curTarget = null;
    }

    private Vector3 getDefaultPositionByTargetPosition() {
        return owner.getMoveMgr().getTargetPositionOrCurPosition().plus(self.getDefaultRelateOwnerPosition().rotateForword(owner.getOrient()));
        //return owner.calcPositionByOrientAndRelatePosition(self.getDefaultRelateOwnerPosition());
    }

    private Vector3 getDefaultPositionByOwnerPosition() {
        return owner.getPosition().plus(self.getDefaultRelateOwnerPosition().rotateForword(owner.getOrient()));
        //return owner.calcPositionByOrientAndRelatePosition(self.getDefaultRelateOwnerPosition());
    }

    private boolean checkFarFromOwnerOrNotInFight() {
        if(!owner.isActive() || owner.isDead())
            return false;

        final Vector3 selfPos = self.getPosition();
        final Vector3 ownerPos = owner.getPosition();
        final double distanctToOwner = selfPos.getSubXZMagnitude(ownerPos);
        if (distanctToOwner > followNearRadius) {
            if (distanctToOwner > followFarRadius) {
                moveMgr.stopAtPosition(getDefaultPositionByOwnerPosition(), self.getOrient());
                switchToAction(IDLE);
            } else {
                onFollow();
            }
            return true;
        } else if(!owner.isFighting()) {
            moveMgr.stopAtCurPosition();
            if(!inAction(IDLE)) {
                switchToAction(IDLE);
            }
            return true;
        }
        return false;
    }

    private boolean checkTargetInvalid() {
        if (curTarget == null || !curTarget.isActive() || curTarget.isDead()) {
            final TargetInfo info = getTarget();
            if (info != null) {
                curTarget = info.target;
                curSkill = info.skill;
            } else {
                if(!owner.isDead())
                    onFollow();
                else
                    switchToAction(IDLE);
                return true;
            }
        }
        return false;
    }


    private double minDistance;
    private Fighter nearestTarget;


    Fighter getNearestTarget(int targetType) {
        final Vector3 attackPos = self.getPosition();
        nearestTarget = null;
        minDistance = 1.0e7;
        if(!owner.isActive() || owner.isDead()) {
            for (Agent agent : self.getMap().getAgents()) {
                if(!(agent instanceof Fighter)) continue;
                final Fighter target = (Fighter) agent;
                final Vector3 targetPos = target.getPosition();
                if (target.isActive() && !target.isDead() && target.canBeattacked() && self.isRelationCamp(target, targetType)) {
                    final double distance = attackPos.getSubXZMagnitude(targetPos);
                    if (distance < minDistance) {
                        nearestTarget = target;
                        minDistance = distance;
                    }
                }
            }
        } else {
            self.getMap().foreachNearbyFighterInAgentMap(owner.getPosition(), guardRadius + followNearRadius, target -> {
                final Vector3 targetPos = target.getPosition();
                if (target.isActive() && !target.isDead() && target.canBeattacked() && self.isRelationCamp(target, targetType)) {
                    final double distance = attackPos.getSubXZMagnitude(targetPos);
                    if (distance < minDistance) {
                        nearestTarget = target;
                        minDistance = distance;
                    }
                }
            });
        }
        return nearestTarget;
    }

    final static class TargetInfo {
        public final Fighter target;
        public final SkillMgr.SkillData skill;

        public TargetInfo(Fighter target, SkillMgr.SkillData skill) {
            this.target = target;
            this.skill = skill;
        }
    }

    TargetInfo getTarget() {
        return getTargetByDistance();
    }

    TargetInfo getTargetByDistance() {
        SkillMgr.SkillData preferSkill = null;
        Fighter preferTarget = null;
        for (SkillMgr.SkillData candi : self.getSkillMgr().getBestSkills()) {
            if (candi != null) {
                final Fighter target = getNearestTarget(candi.target);
                if (target != null && (preferTarget == null || candi.activeTime < preferSkill.activeTime
                        || (preferSkill.skilldmg.isnormal && !candi.skilldmg.isnormal && candi.activeTime <= self.getNow()))) {
                    preferSkill = candi;
                    preferTarget = target;
                }
            }
        }
        return preferTarget != null ? new TargetInfo(preferTarget, preferSkill) : null;
    }
}
