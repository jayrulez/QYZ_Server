package map.map;

import cfg.ectype.EctypeState;
import cfg.fight.StateType;
import map.agent.Agent;
import map.agent.Fighter;
import map.buff.effect.InternEffcteIds;
import map.msg.SBeginFight;
import map.msg.SCountDown;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2016/4/29.
 */
public abstract class MultiPlayerEctype extends Ectype {

    public static class Builder extends Ectype.Builder {
        public HashSet<Long> roles = new HashSet<>();
        public long countDownDurationTime;
    }

    private final HashSet<Long> roles;
    private final long countDownDurationTime;

    protected int state;


    private boolean inPrepare() {
        return state == EctypeState.PREPARE;
    }

    public MultiPlayerEctype(Builder b) {
        super(b);

        this.roles = b.roles;
        this.countDownDurationTime = b.countDownDurationTime;

        this.state = EctypeState.PREPARE;
    }

    protected Set<Long> getRoles(){
        return roles;
    }

    @Override
    public boolean admit(long roleid) {
        return notEnd() && roles.contains(roleid);
    }

    @Override
    public void init() {
        super.init();

        if(countDownDurationTime > 0) {
            startCountDown();
        } else {
            this.state = EctypeState.START;
            onBattleBegin();
            startRemainTime();
        }
    }

    @Override
    public void onAgentEnter(Agent agent) {
        super.onAgentEnter(agent);
        if(inPrepare()) {
            if(agent instanceof Fighter) {
                ((Fighter)agent).getStateMgr().setStateForever(InternEffcteIds.NOT_FIGHT, StateType.NOT_FIGHT);
            }
        }
    }

    protected void startCountDown() {
        sendContextMsg(new SCountDown(getNow() + this.countDownDurationTime));
        schedule(this::countDownEnd, this.countDownDurationTime);
    }

    protected void countDownEnd() {
        this.state = EctypeState.START;
        sendContextMsg(new SBeginFight());

        for(Agent a : getAgents()) {
            if(a instanceof Fighter) {
                ((Fighter)a).getStateMgr().clearState(InternEffcteIds.NOT_FIGHT);
            }
        }
        startRemainTime();
        onBattleBegin();
    }

    protected abstract void onBattleBegin();
}
