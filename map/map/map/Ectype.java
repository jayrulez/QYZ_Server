package map.map;

import cfg.CfgMgr;
import cfg.Const;
import cfg.fight.PKState;
import cfg.map.Reason;
import cfg.map.ReviveType;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import map.MapUtils;
import map.agent.*;
import map.msg.*;
import map.skill.DamageParam;
import map.skill.DeathParam;
import pathfinding.Vector3;
import xdb.Trace;
import xio.Protocol;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by huangqiang on 2016/1/11.
 */
public abstract class Ectype extends GameMap {
    private final static AtomicLong nextId = new AtomicLong();

    protected static void initCommon(Builder b, int ectypeid, boolean istaskectype, int serverid) {
        b.isTaskEctype = istaskectype;
        final cfg.ectype.EctypeBasic basicCfg = CfgMgr.ectypebasic.get(ectypeid);
        b.basiccfg = basicCfg;
        b.mapid = MapUtils.makeEctypeId(nextId.getAndIncrement(), basicCfg.type);
        b.subid = ectypeid;
        b.serverid = serverid;

        b.scene = basicCfg.scenename;
        b.landscapeid = basicCfg.landscapeid;
        b.regionsetid = basicCfg.regionsetid;

        b.xcellNum = 1;
        b.zcellNum = 1;

        b.baseUpdateInterval = 100;
        b.nearbyUpdateInterval = 1000;
        b.normalUpdateInterval = 100;
        b.controllerUpdateInterval = 1000;

        b.maxDefencerBodyHeight = 10;
        b.maxDefencerBodyRadius = 4;

        b.innerSightRadius = basicCfg.sightradius;
        b.outerSightRadius = basicCfg.sightradius + 5;

        b.initOrient = MapUtils.createOrient(basicCfg.startrotation);
        b.initPosition = MapUtils.c2p(basicCfg.startposition);

        b.statistic = true;
        b.canPetRevive = true;
        b.needSaveContextProtocol = true;

        b.useBroadcastPolicy = true;
    }

    protected final int ectypeid;
    protected final long createTime;
    protected long timeoutTime;
    protected final cfg.ectype.EctypeBasic basiccfg;
    protected final boolean statistic;

    private Vector3 initPosition;
    private Vector3 initOrient;


    private final boolean isTaskEctype;

    protected boolean readyCountTime;
    protected long remainTime;
    protected long endTime;
    protected ErrorCode result;

    protected static class PlayerOrPetStatisticInfo {
        public final long agentid;
        public final long ownerid;
        public int deadCount;
        public long deadTime;
        public int killCount;
        public int curContinueKillCount;
        public int maxContinueKillCount;
        public int damage;
        public int suffer;
        public int rune;

        PlayerOrPetStatisticInfo(long aid, long ownerid) {
            this.agentid = aid;
            this.ownerid = ownerid;
        }
    }

    protected Map<Long, PlayerOrPetStatisticInfo> playerStatInfo = new HashMap<>();
    protected Map<Long, Fighter> cacheAgents = new HashMap<>();

    public static class Builder extends GameMap.Builder {
        cfg.ectype.EctypeBasic basiccfg;
        boolean isTaskEctype;
        boolean statistic;
        boolean needSaveContextProtocol;
    }

    private final static int CLOSE_DELAY = 1000 * 60;
    public Ectype(Builder b) {
        super(b);

        this.ectypeid = b.subid;
        this.isTaskEctype = b.isTaskEctype;
        this.createTime = System.currentTimeMillis();
        this.timeoutTime = createTime + (b.basiccfg.totaltime + 600) * 1000L;
        this.readyCountTime = false;
        this.remainTime = b.basiccfg.totaltime * 1000L;
        this.endTime = Const.NULL;
        this.basiccfg = b.basiccfg;
        this.initPosition = b.initPosition;
        this.initOrient = b.initOrient;
        this.result = null;
        this.statistic = b.statistic;
        this.needSaveContextProtocol = b.needSaveContextProtocol;
    }

    @Override
    public String toString() {
        return String.format("%s{mapid:%s type:%s ectypeid:%s players:%s agents:%s}",
                this.getClass().getSimpleName(), getMapid(), getType(), ectypeid, players.size(), getAgents().length);
    }

    public final cfg.ectype.EctypeBasic getCfg() {
        return basiccfg;
    }

    public final int getEctypeid() {
        return ectypeid;
    }

    public final boolean isSuccCompleted() {
        return result == ErrorCode.OK;
    }

    public final boolean isTaskEctype() {
        return isTaskEctype;
    }

    public Vector3 getInitPosition() {
        return initPosition;
    }

    protected void setInitPosition(Vector3 initPosition) {
        this.initPosition = initPosition;
    }

    public Vector3 getInitOrient() {
        return initOrient;
    }

    protected void setInitOrient(Vector3 initOrient) {
        this.initOrient = initOrient;
    }

    public boolean notEnd() {
        return result == null;
    }

    public boolean hasEnd() {
        return result != null;
    }

    public long getCostTime() {
        return basiccfg.totaltime * 1000 - remainTime;
    }

    public int getDeadCount(long roleid) {
        final PlayerOrPetStatisticInfo si = playerStatInfo.get(roleid);
        return si != null ? si.deadCount : 0;
    }

    public void startRemainTime() {
        this.readyCountTime = true;
    }

    protected boolean checkNotExceedMaxDeadCount(Player player, int deadCount) {
        return deadCount <= basiccfg.reviveinfo.maxcount;
    }

//    @Override
//    public boolean isEnemy(Fighter a, Fighter b) {
//        return MapModule.campRelations[a.getCamp()][b.getCamp()] == Relation.ENEMY;
//    }
//
//    @Override
//    public boolean isFriend(Fighter a, Fighter b) {
//        return MapModule.campRelations[a.getCamp()][b.getCamp()] == Relation.FRIEND;
//    }

    public void init() {
        super.init();


        final cfg.ectype.ReviveInfo reviveInfo = basiccfg.reviveinfo;
        addListener(Listener.DEATH, (go, evtid, param) -> {
            final DeathParam dp = (DeathParam)param;
            final Fighter defencer = dp.defencer;
            final Fighter attacker = dp.attacker;
            if (defencer.isPlayerOrFakePlayerOrPet()) {
                final PlayerOrPetStatisticInfo dpsi = getOrCreate(defencer);
                dpsi.deadCount++;
                dpsi.deadTime = getNow();
                dpsi.curContinueKillCount = 0;

                if (defencer.isPlayerOrFakePlayer()) {
                    final Player player = defencer.asPlayerOrFakePlayer();
                        player.sendNotRoleMsg(new SDeadCount(dpsi.deadCount));
                        if (reviveInfo.maxcount == Const.NULL || checkNotExceedMaxDeadCount(player, dpsi.deadCount)) {
                            if (reviveInfo.time > 0) {
                                schedule(() -> {
                                    revivePlayer(player, reviveInfo.type);
                                }, reviveInfo.time * 1000);
                            }
                        } else {
                            onPlayerExceedMaxDeadCount(player);
                        }
                    }

            }
            if(attacker.isPlayerOrFakePlayerOrPet()) {
                final PlayerOrPetStatisticInfo apsi = getOrCreate(attacker);
                apsi.killCount++;
                apsi.curContinueKillCount++;
                apsi.maxContinueKillCount = Math.max(apsi.curContinueKillCount, apsi.maxContinueKillCount);
            }
        });

        if(this.statistic) {
            addListener(Listener.BE_DAMAGE, (go, evtid, param) -> {
                final DamageParam dp = (DamageParam)param;
                if(dp.attacker.isPlayerOrFakePlayerOrPet()) {
                    final PlayerOrPetStatisticInfo apsi = getOrCreate(dp.attacker);
                    apsi.damage += dp.damage;
                }
                if(dp.defencer.isPlayerOrFakePlayerOrPet()) {
                    final PlayerOrPetStatisticInfo dpsi = getOrCreate(dp.defencer);
                    dpsi.suffer += dp.damage;
                }
            });
        }
    }


    protected void close() {
        hasClose = true;
        for(Player player : new ArrayList<>(players.values())) {
            if(player.isActive()) {
                internPlayerLeave(player, result == ErrorCode.OK ? Reason.ENTER_PREV_MAP : Reason.MAP_CLOSE_KICKOUT);
            }
        }
        schedule(this::stop, 10 * 1000);
    }

    @Override
    public void revivePlayer(Player player, int reviveType) {
        if(!player.isActive() || !player.isDead()) {
            Trace.error("player:{} isn't active or not isdead. can't revive", player);
            return;
        }
        final PlayerOrPetStatisticInfo si = getOrCreate(player);
        final cfg.ectype.ReviveInfo ri = basiccfg.reviveinfo;
        if(ri.maxcount != Const.NULL && si.deadCount > ri.maxcount) {
            Trace.error("player:{} deadcount:{} exceed max revivecount:{} can't revive", player, si.deadCount, ri.maxcount);
            return;
        }
        // 因为是 schedule revive,不能用now = getNow
        // 否则可能出现明明到了复活时间,检查却失败的bug
        final long now = System.currentTimeMillis();
        if(ri.time > 0 && si.deadTime + ri.time * 1000L > now) {
            Trace.error("player:{} deadtime:{} revivetime:{} now:{}. can't revive", player, si.deadTime, ri.time, now);
            return;
        }
        si.deadTime = 0;

        doRevive(player);
    }

    protected void doRevive(Player player) {
        switch (basiccfg.reviveinfo.type) {
            case ReviveType.CUR_POSITION: {
                player.reviveAtCurPosition();
                break;
            }
            case ReviveType.REVIVE_POSITION: {
                player.revive(getInitPosition(), getInitOrient());
                break;
            }
            default: {
                xdb.Trace.error("unknown revivetyp:" + basiccfg.reviveinfo.type);
            }
        }
    }

    protected PlayerOrPetStatisticInfo getOrCreate(Fighter fighter) {
        final long aid = fighter.getAid();
        PlayerOrPetStatisticInfo psi = playerStatInfo.get(aid);
        if(psi == null) {
            psi = new PlayerOrPetStatisticInfo(aid, fighter.getOwner().getAid());
            playerStatInfo.put(aid, psi);
        }
        return psi;
    }

    @Override
    public void onPlayerEnter(Player player) {
        player.setPkstate(PKState.PEACE);

        final PlayerOrPetStatisticInfo psi = getOrCreate(player);
        final cfg.ectype.ReviveInfo reviveInfo = basiccfg.reviveinfo;
        // 如果玩家上次在地图中是死亡状态.
        // 判断是否到复活时间
        if(psi.deadTime > 0) {
            player.setDead();
            if(reviveInfo.time > 0 && psi.deadCount <= reviveInfo.maxcount) {
                if(psi.deadTime + reviveInfo.time * 1000 > getNow()) {
                    Trace.info("Ectype.onPlayerEnter player:{} hasn't revive.");
                } else {
                    Trace.info("Ectype.onPlayerEnter player:{} revive.");
                    addDeferTask(() -> revivePlayer(player, reviveInfo.type));
                }
            }
        }
        if(psi.deadCount > 0)
            player.sendNotRoleMsg(new SDeadCount(psi.deadCount));
        player.sendXdb(new MEnterEctypeLogger(ectypeid));
    }

    protected SEctypeMemberEnter getEnterInfo(Player player){
        SEctypeMemberEnter ret = new SEctypeMemberEnter();
        ret.id = player.getRoleid();
        ret.name = player.getName();
        ret.level = player.getLevel();
        ret.serverid = player.getServerid();
        ret.gender = player.getGender();
        ret.profession = player.getProfession();
        ret.viplevel = player.getVipLevel();
        ret.familyname = player.getFamilyName();
        return ret;
    }

    public void playerPraise(long roleid, CPraiseMember msg) {
        SPraiseMember sPraiseMember = new SPraiseMember(roleid, msg.member);
        sendPlayer(roleid, sPraiseMember);
        sendPlayer(msg.member, sPraiseMember);
    }

    protected abstract void broadcastToSameCamp(Player player);

    protected void sendToEachother(Player player1, Player player2){
        if(player1 == null || player2 == null){
            return;
        }
        sendPlayer(player1.getRoleid(), getEnterInfo(player2));
        sendPlayer(player2.getRoleid(), getEnterInfo(player1));
    }

    protected abstract void onPlayerExceedMaxDeadCount(Player player);

    @Override
    public void onPlayerLeave(Player player) {}

    @Override
    public void onAgentEnter(Agent agent) {
        if(agent.isPlayerOrFakePlayerOrPet()) {
            cacheAgents.put(agent.getAid(), (Fighter)agent);
        }
    }

    @Override
    public void onAgentLeave(Agent agent) {}

    public void broadcastNotContextMsg(xio.Protocol proto) {
        for(Player player : players.values()) {
            if(player.isActive())
                player.sendNotRoleMsg(proto);
        }
    }

    private void broadcastToReady(xio.Protocol proto) {
        for(Player player : players.values()) {
            if(player.isActive() && player.isReady())
                player.sendNotRoleMsg(proto);
        }
    }

    public void sendPlayer(long roleid, xio.Protocol proto) {
        final Player player = getPlayer(roleid);
        if(player != null)
            player.sendNotRoleMsg(proto);
    }

    protected final void success() {
        checkEnd(ErrorCode.OK);
    }


    protected final void checkEnd(ErrorCode err) {
        if(hasEnd()) return;
        this.result = err;
        this.endTime = System.currentTimeMillis();
        Trace.info("ectype:{} end. err:{} endtime:{} costtime:{}", this, err, endTime, basiccfg.totaltime - remainTime/1000);
        if(false) {
            System.out.printf("ectypeid:%s name:%s end. costtime:%d seconds\n", ectypeid, basiccfg.ectypename, basiccfg.totaltime - remainTime/1000);
            for(PlayerOrPetStatisticInfo si : playerStatInfo.values()) {
                final Agent agent = cacheAgents.get(si.agentid);
                final String owner = agent != null ? agent.getName() : "null";
                final String name = agent != null ? agent.getName() : "null";
                System.out.printf("agentid:%d name:%s owner:%s damge:%s suffer:%d deadcount:%d killcount:%d countinuekillcount:%d rune:%d\n",
                        si.agentid, name, owner, si.damage, si.suffer, si.deadCount, si.killCount, si.maxContinueKillCount, si.rune);
            }
        }
        setSuspend();
        addDeferTask(() -> {
            onFail(err);
            //After ectype end, logger
            players.values().forEach(p -> p.sendXdb(new MEndEctypeLogger(ectypeid, err.getErrorId(), endTime - createTime)));
        });
    }


    private final boolean needSaveContextProtocol;
    private final List<MapContexProtocol> keepContextProtocols = new ArrayList<>();
    protected void sendContextMsg(xio.Protocol msg) {
        if(needSaveContextProtocol) {
            final MapContexProtocol re = new MapContexProtocol();
            re.index = keepContextProtocols.size();
            final OctetsStream os = new OctetsStream();
            os.marshal(msg.getType());
            os.marshal(msg);
            re.data = os;
            keepContextProtocols.add(re);
            broadcastToReady(re);
        } else {
            broadcastToReady(msg);
        }
    }

    protected void sendAllContextMsg(Player player) {
        final MapContexProtocols msg = new MapContexProtocols();
        msg.count = keepContextProtocols.size();
        for(MapContexProtocol p : keepContextProtocols) {
            msg.data.insert(msg.data.size(), p.data);
        }
        player.sendNotRoleMsg(msg);
    }

    @Override
    public void onPlayerReady(Player player) {
        if(this.needSaveContextProtocol) {
            sendAllContextMsg(player);
        }
        broadcastToSameCamp(player);
    }

    protected abstract void normalUpdate(long now);
    protected abstract void onFail(ErrorCode err);


    private boolean hasClose = false;
    @Override
    public void update(long now) {
        super.update(now);

        if(notEnd()) {
            if(!isSuspend()) {
                normalUpdate(now);
                if(readyCountTime)
                    remainTime -= getDeltaTime();
            }
            if(now >= timeoutTime || remainTime <= 0) {
                checkEnd(ErrorCode.ECTYPE_TIMEOUT);
            }
        } else {
            if((now > timeoutTime + CLOSE_DELAY || (endTime != Const.NULL && now > endTime + CLOSE_DELAY)) && !hasClose) {
                close();
            }
        }
    }

    protected int getCamp(Fighter fighter){
        return fighter.getCamp();
    }

    public SEctypeStatistic createSEctypeStatistic() {
        final SEctypeStatistic re = new SEctypeStatistic();
        final Map<Integer, TeamStatistic> teams = new TreeMap<>();  //用treemap，使结果按队伍顺序排序
        playerStatInfo.forEach((aid, psi) -> {
            final MemberStatistic ms = new MemberStatistic();
            final Fighter fighter = cacheAgents.get(aid);
            int camp = 0;
            if(fighter.isPlayerOrFakePlayer()) {
                final Player player = (Player)fighter;
                ms.name = player.getName();
                ms.ownername = "";
                camp = getCamp(fighter);
            } else if(fighter.isPet()) {
                final APet pet = (APet)fighter;
                ms.name = pet.getName();
                ms.ownername = pet.getOwner().getName();
                camp = getCamp(pet.getOwner());
            }

            TeamStatistic ts = teams.get(camp);
            if(ts == null) {
                ts = new TeamStatistic();
                teams.put(camp, ts);
            }
            ms.kill = psi.killCount;
            ms.damage = psi.damage;
            ms.rune = psi.rune;

            ts.members.add(ms);
        });
        re.teams.addAll(teams.values());
        return re;
    }

    protected HashMap<Long, PlayerOrPetStatisticInfo> statisticByRoleid() {
        final HashMap<Long, PlayerOrPetStatisticInfo> stats = new HashMap<>();
        playerStatInfo.forEach((aid, psi) -> {
            final long roleid = psi.ownerid;
            PlayerOrPetStatisticInfo si = stats.get(roleid);
            if(si == null) {
                si = new PlayerOrPetStatisticInfo(roleid, roleid);
                stats.put(roleid, si);
            }
            si.killCount += psi.killCount;
            si.damage += psi.damage;
            si.deadCount += psi.deadCount;
            si.suffer += psi.suffer;
            si.maxContinueKillCount = Math.max(si.maxContinueKillCount, psi.maxContinueKillCount);
        });
        return stats;
    }


    public Protocol createSEctypeDamageStatistic() {
        final SEctypeDamageStatistic re = new SEctypeDamageStatistic();
        for(Map.Entry<Long, PlayerOrPetStatisticInfo> e : playerStatInfo.entrySet()) {
            re.agentdamages.put(e.getKey(), (long)e.getValue().damage);
        }
        return re;
    }

    public boolean eatRune(Player player, ARune arune) {
        boolean result = super.eatRune(player, arune);
        if(result && this.statistic) {
            getOrCreate(player).rune++;
        }
        return result;
    }
}
