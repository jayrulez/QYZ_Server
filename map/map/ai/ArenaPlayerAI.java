package map.ai;

import cfg.fight.AgentType;
import common.ErrorCode;
import map.map.GameMap;
import xdb.Trace;
import map.agent.Agent;
import map.agent.Fighter;
import map.agent.MoveMgr;
import map.agent.SkillMgr;
import map.skill.Skill;
import pathfinding.Vector3;

/**
 * Created by huangqiang on 2016/3/7.
 */
public class ArenaPlayerAI extends AI {

    private final MoveMgr moveMgr;
    private final SkillMgr skillMgr;
    public ArenaPlayerAI(Fighter self, AIFactory.Builder b) {
        super(self);

        moveMgr = self.getMoveMgr();
        skillMgr = self.getSkillMgr();

        switchToAction(IDLE);
    }


    private final Action IDLE = this::doIdle;
    private final Action WALK_TO_TARGET = this::doWalkToTarget;
    private final Action ATTACK = this::doAttack;


    private boolean doIdle(long now) {
        final Fighter target = getTarget();
        if (target != null) {
            onWalkToTarget(target);
            return true;
        }
        return false;
    }

    private boolean doWalkToTarget(long now) {
        final Vector3 curPos = self.getPosition();
        if(!curTarget.isActive() || curTarget.isDead()) {
            final Fighter target = getTarget();
            if (target != null) {
                curTarget = target;
            } else {
                switchToAction(IDLE);
                return false;
            }
        }
        final Vector3 targetPos = curTarget.getPosition();
        if(curPos.getSubXZMagnitude(targetPos) <= skillMgr.getMaxAvailableSkillDistance()) {
            moveMgr.stopAtCurPosition();
            switchToAction(ATTACK);
            return true;
        }
        moveMgr.setTarget(targetPos);
        return false;
    }

    private boolean doAttack(long now) {
        if(!skillMgr.isCurSkillFinished()) {
            return false;
        }
        final Vector3 curPos = self.getPosition();
        if(!curTarget.isActive() || curTarget.isDead()) {
            final Fighter target = getTarget();
            if (target != null) {
                onWalkToTarget(target);
                return true;
            } else {
                switchToAction(IDLE);
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

            self.performSkillAndNotify(param);
        }
        return false;
    }

    private Fighter curTarget;


    Fighter getTarget() {
        if(!self.canSkillattack() && !self.canNormalattack()) return null;
        final Vector3 attackPos = self.getPosition();
        Fighter nearestTarget = null;
        double minDistance = 1.0e7;
        final GameMap gameMap = self.getMap();
        for(Agent agent : self.getMap().getAgents()) {
            if(agent.isActive() && agent.isFighter()) {
                final Fighter target = (Fighter) agent;
                if (!target.isDead() && target.canBeattacked() && gameMap.isEnemy(self, target)) {
                    // 优先打玩家
                    if (target.isPlayer())
                        return target;
                    final double distance = attackPos.getSubXZMagnitude(target.getPosition());
                    if (distance < minDistance) {
                        nearestTarget = target;
                        minDistance = distance;
                    }
                }
            }
        }
        return nearestTarget;
    }

    private void onWalkToTarget(Fighter target) {
        switchToAction(WALK_TO_TARGET);
        curTarget = target;
        //Trace.info("ArenaPlayerAI. fakeplayer:{} walk to target:{}", self, target);
    }
}
