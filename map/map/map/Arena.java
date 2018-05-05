package map.map;

import cfg.CfgMgr;
import cfg.ectype.EctypeState;
import cfg.fight.CampType;
import cfg.fight.StateType;
import common.ErrorCode;
import common.Time;
import map.MapUtils;
import map.agent.*;
import map.ai.AIFactory;
import map.buff.effect.InternEffcteIds;
import map.msg.*;

/**
 * Created by huangqiang on 2016/2/24.
 */
public class Arena extends OnePlayerEctype {

    public static Arena create(XCreateArenaEctype builder) {
        final Builder b = new Builder();
        initCommon(b, builder.ectypeid, false, builder.serverid);
        b.roleid = builder.roleid;
        b.profession = builder.profession;
        b.failOnLeave = true;
        b.canPetRevive = false;

        b.useBroadcastPolicy = false;

        b.opponent = builder.opponent;
        return new Arena(b);
    }

    public static class Builder extends OnePlayerEctype.Builder {
        public PlayerBuilder opponent;
    }

    private final PlayerBuilder opponentBuilder;
    private final cfg.ectype.ArenaEctype acfg;
    private FakePlayer opponent;
    private int state;

    public Arena(Builder b) {
        super(b);
        this.opponentBuilder = b.opponent;
        this.acfg = CfgMgr.arenaectype;
    }

    public void init() {
        super.init();

        setInitPosition(MapUtils.c2p(acfg.playerbornposition));

        final AgentBuilder ab = opponentBuilder.basic.basic;
        ab.position = common.Utils.c2m(acfg.aibornposition);
        ab.orient = new map.msg.Vector3(1, 0, 0);
        opponent = createFakePlayer(opponentBuilder);
        // 在倒计时结束前,怪物不能攻击,索性就让它不动吧
        opponent.getStateMgr().setStateForever(InternEffcteIds.NOT_FIGHT, StateType.NOT_FIGHT);

        addListener(Listener.DEATH, (go, evtid, param) -> {
            checkResult();
        });

        this.state = EctypeState.PREPARE;
    }

    @Override
    public boolean canPetRevive() {
        return false;
    }

    @Override
    protected void normalUpdate(long now) {

    }

    @Override
    protected void onPlayerExceedMaxDeadCount(Player player) {

    }

    private void checkResult() {
        if(hasEnd()) return;

        int playerAliveNum = 0;
        int robotAliveNum = 0;
        for(Agent agent : getAgents()) {
            final Fighter fighter = (Fighter)agent;
            if(fighter.isActive() && !fighter.isDead()) {
                switch (fighter.getCamp()) {
                    case CampType.PLAYER: {
                        ++playerAliveNum;
                        break;
                    }
                    case CampType.ENEMY: {
                        ++robotAliveNum;
                        break;
                    }
                }
            }
        }
        if(playerAliveNum == 0) {
            checkEnd(ErrorCode.ECTYPE_PLAYER_DEAD);
        } else if(robotAliveNum == 0) {
            success();
        }
    }

    @Override
    protected void onFail(ErrorCode err) {
        Player.sendXdb(roleid, new MEndArenaEctype(err.getErrorId(), opponent.getRoleid()));
        Player.sendXdb(roleid, new MEndArenaLogger(ectypeid, err.getErrorId(), endTime - createTime, opponentBuilder.basic.basic.agentid));
    }

    @Override
    public void onPlayerEnter(Player player) {
        super.onPlayerEnter(player);
        player.sendXdb(new MEnterArenaLogger(ectypeid, opponentBuilder.basic.basic.agentid));
    }

    @Override
    public void onAgentEnter(Agent agent) {
        super.onAgentEnter(agent);
        if(state == EctypeState.PREPARE) {
            ((Fighter)agent).getStateMgr().setStateForever(InternEffcteIds.NOT_FIGHT, StateType.NOT_FIGHT);
        }
    }

    @Override
    public void sendPlayerEnter(Player player) {
        final SEnterArenaEctype re = new SEnterArenaEctype();
        re.id = getMapid();
        re.ectypeid = getEctypeid();
        re.state = state;
        re.remaintime = remainTime;
        player.sendNotRoleMsg(re);
    }

    @Override
    protected void onReady() {
        super.onReady();
        sendContextMsg(new SCountDown(getNow() + Time.toMilliseconds(acfg.countdown)));
        schedule(() -> {
            sendContextMsg(new SBeginFight());
            for(Agent agent : getAgents()) {
                ((Fighter)agent).getStateMgr().clearState(InternEffcteIds.NOT_FIGHT);
            }
            this.state = EctypeState.START;
            startRemainTime();
        }, acfg.countdown * 1000L);
    }

}
