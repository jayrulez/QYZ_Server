package map.map;

import cfg.CfgMgr;
import cfg.Const;
import cfg.ectype.EctypeState;
import cfg.fight.CampType;
import cfg.fight.StateType;
import common.ErrorCode;
import common.Time;
import map.MapUtils;
import map.agent.Agent;
import map.agent.Fighter;
import map.agent.Listener;
import map.agent.Player;
import map.buff.effect.InternEffcteIds;
import map.msg.*;

/**
 * Created by HuangQiang on 2016/5/16.
 */
public class HuiWu extends Ectype {
    public static HuiWu create(XCreateHuiWu msg) {
        final Builder b = new Builder();
        initCommon(b, msg.ectypeid, false, msg.serverid);

        b.innerSightRadius = CfgMgr.roleconfig.oneplayermapinnersightradius;
        b.outerSightRadius = CfgMgr.roleconfig.oneplayermapoutersightradius;

        b.profession = msg.profession;
        b.roundIndex = msg.roundindex;
        b.battleIndex = msg.battleindex;
        b.player1 = msg.player1;
        b.player2 = msg.player2;

        b.canPetRevive = false;

        return new HuiWu(b);
    }

    public static class Builder extends Ectype.Builder {
        public int profession;
        public int roundIndex;
        public int battleIndex;
        public PlayerBuilder player1;
        public PlayerBuilder player2;
    }

    private final cfg.huiwu.HuiWu hcfg;
    private cfg.huiwu.BattleTimeControler btcfg;
    private final int profession;
    private final int roundIndex;
    private final int battleIndex;
    private final PlayerBuilder player1;
    private final PlayerBuilder player2;
    private final long roleid1;
    private final long roleid2;

    private int state;

    private final static int CAMP1 = CampType.PLAYER_BLUE;
    private final static int CAMP2 = CampType.PLAYER_RED;

    public HuiWu(Builder b) {
        super(b);

        this.hcfg = CfgMgr.huiwu;
        this.profession = b.profession;
        this.roundIndex = b.roundIndex;
        this.battleIndex = b.battleIndex;
        this.player1 = b.player1;
        this.player2 = b.player2;
        this.roleid1 = b.player1.basic.basic.agentid;
        this.roleid2 = b.player2.basic.basic.agentid;
        this.btcfg = hcfg.battletime.get(roundIndex - 1);

        this.state = EctypeState.PREPARE;
    }

    public void init() {
        super.init();

        addListener(Listener.DEATH, (go, evtid, param) -> {
            if(anyTeamAllDead()) {
                onFail(ErrorCode.ECTYPE_PLAYER_DEAD);
            }
        });

        sendContextMsg(new SCountDown(getNow() + Time.toMilliseconds(btcfg.wait)));
        schedule(this::onBattleBegin, btcfg.wait * 1000L);
    }

    private boolean inPrepare() {
        return state == EctypeState.PREPARE;
    }

    @Override
    public final boolean admit(long roleid) {
        if(hasEnd() || (roleid != roleid1 && roleid != roleid2))
            return false;
        final Player player = players.get(roleid);
        // 如果已经创建了机器人.不能再进场了
        return player == null || player.isPlayer();
    }

    @Override
    protected void broadcastToSameCamp(Player player) {
        //无需实现
    }

    @Override
    protected void initPlayerPositionAndOrient(Player player) {
        player.initPosition(MapUtils.c2p(player.getRoleid() == roleid1 ? hcfg.player1bornposition : hcfg.player2bornposition));
        player.initOrient(MapUtils.createOrient(0));
    }

    @Override
    protected void onPlayerExceedMaxDeadCount(Player player) {

    }

    @Override
    protected void onPrecreatePlayer(PlayerBuilder b) {
        super.onPrecreatePlayer(b);
        b.basic.camp = (b.basic.basic.agentid == roleid1) ? CAMP1 : CAMP2;
    }

    @Override
    protected void normalUpdate(long now) {

    }

    private boolean anyTeamAllDead() {
        final Agent[] all = getAgents();
        if(all.length == 0) return true;
        int camp = Const.NULL;
        for(Agent a : all) {
            final Fighter f = (Fighter)a;
            if(camp == Const.NULL)
                camp = f.getCamp();
            else if(camp != f.getCamp())
                return true;
        }
        return false;
    }

    private int calcResult() {
        final Agent[] all = getAgents();
        boolean team1Alive = false;
        boolean team2Alive = false;
        for(Agent a : all) {
            final Fighter p = (Fighter)a;
            if(p.isActive() && !p.isDead()) {
                team1Alive = team1Alive || p.getCamp() == CAMP1;
                team2Alive = team2Alive || p.getCamp() == CAMP2;
            }
        }
        if(team1Alive && !team2Alive) {
                return 1;
        } else if(!team1Alive && team2Alive) {
            return -1;
        } else {
            return Integer.compare(player1.combatpower ,player2.combatpower);
        }
    }

    private void sendResult(long roleid, int result) {
        final SEndHuiWu re = new SEndHuiWu();
        re.roundindex = roundIndex;
        re.battleindex = battleIndex;
        re.result = result;
        sendPlayer(roleid, re);
    }

    @Override
    protected void onFail(ErrorCode err) {
        final int result = calcResult();
        sendResult(roleid1, result);
        sendResult(roleid2, -result);

        final MEndHuiWu msg = new MEndHuiWu();
        msg.result = result;
        msg.profession = profession;
        msg.roundindex = roundIndex;
        msg.battleindex = battleIndex;
        sendXdbServer(msg);
    }

    @Override
    public void sendPlayerEnter(Player player) {
        final SEnterHuiWu re = new SEnterHuiWu();
        re.id = getMapid();
        re.ectypeid = ectypeid;
        re.state = this.state;
        re.remaintime = remainTime;
        player.sendNotRoleMsg(re);
    }

    @Override
    public void onAgentEnter(Agent agent) {
        super.onAgentEnter(agent);
        if(inPrepare()) {
            ((Fighter)agent).getStateMgr().setStateForever(InternEffcteIds.NOT_FIGHT, StateType.NOT_FIGHT);
        }
    }

    private void onBattleBegin() {
        if(hasEnd()) return;
        state = EctypeState.START;
        if(players.isEmpty()) {
            checkEnd(ErrorCode.OK);
        } else {
            for (Agent agent : getAgents()) {
                final Fighter fighter = (Fighter) agent;
                fighter.getStateMgr().clearState(InternEffcteIds.NOT_FIGHT);
            }

            if(!players.containsKey(roleid1)) {
                createFakePlayer(player1);
            }
            if(!players.containsKey(roleid2)) {
                createFakePlayer(player2);
            }

            sendContextMsg(new SBeginFight());
            startRemainTime();
        }
    }
}
