package map.ai;

import map.agent.Fighter;
import map.agent.HostilityMgr;
import map.agent.SkillMgr;
import map.skill.Skill;
import pathfinding.Vector3;
import xdb.Trace;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

/**
 * Created by huangqiang on 2016/2/22.
 */
public abstract class AI {
    protected final Fighter self;
    protected final static Action DO_NOTHING = now ->  false;
    //protected  final long updateInterval;
    protected long nextUpdateTime;
    public AI(Fighter self) {
        this.self = self;
        //this.updateInterval = cfg.ai.AI.DEFAULT_UPDATE_INTERVAL;
        //this.nextUpdateTime = self.getNow();
    }

    protected interface Action {
        boolean run(long now);
    }

    private Action curAction;

    protected final void switchToAction(Action action) {
        curAction = action;
    }

    protected final void switchToEnd() {
        curAction = DO_NOTHING;
    }

    protected final boolean inAction(Action action) {
        return curAction == action;
    }

    protected final void setNextUpdateAfter(long time) {
        this.nextUpdateTime = self.getNow() + time;
    }

    public final void update(long now) {
        if(this.nextUpdateTime <= now) {
            // 保持update的稳定性.不至于因为时间偏差而少调或者一次性多调
        //    this.nextUpdateTime = Math.max(this.nextUpdateTime + this.updateInterval, now + this.updateInterval - 100);
            doUpdate(now);
        }
    }

    private boolean logError = false;
    private final static int MAX_RUN_CYCLE = 10;
    public final void doUpdate(long now) {
        int count = 0;
        while(curAction.run(now) && ++count <= MAX_RUN_CYCLE);
        if(count >= MAX_RUN_CYCLE && !logError) {
            Trace.error("ai:{} run exceed max cycle. has forever loop?", this);
        }
    }

}
