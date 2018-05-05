package map.ai;

import cfg.map.PatrolType;
import cfg.map.Reason;
import map.agent.Fighter;
import map.agent.Listener;
import map.skill.DeathParam;
import pathfinding.Vector3;
import xdb.Trace;

/**
 * Created by HuangQiang on 2016/7/19.
 */
public class RunOnceAI extends AI {
    final private Vector3 targetPos;
    public RunOnceAI(Fighter self, AIFactory.Builder b) {
        super(self);

        this.targetPos = b.patrolPath != null && !b.patrolPath.isEmpty() ? b.patrolPath.get(0) : null;
        if(this.targetPos == null) {
            Trace.error("RunOnceAI. {} patrolpath can't be empty!", self);
        }

        switchToAction(this::moveToEnd);
    }


    private boolean moveToEnd(long now) {
        if(self.getPosition().getSubXZMagnitude(targetPos) < 0.1) {
            switchToEnd();
            self.getMap().leave(self, Reason.COLLECTED);
        } else {
            self.getMoveMgr().setTarget(targetPos);
        }
        return false;
    }
}
