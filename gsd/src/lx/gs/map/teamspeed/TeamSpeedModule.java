package lx.gs.map.teamspeed;

import cfg.CfgMgr;
import cfg.Const;
import cfg.cmd.ConfigId;
import cfg.common.DayTimeRange;
import cfg.ectype.MatchType;
import cfg.ectype.SpeedLvMsg;
import cfg.ectype.TeamSpeed;
import cfg.tips.LocationType;
import common.ErrorCode;
import common.RefObject;
import common.TaskQueue;
import common.Time;
import gs.RoleLoginListener;
import lx.gs.SError;
import lx.gs.activity.ActivityModule;
import lx.gs.activity.SimpleDailyActivity;
import lx.gs.cmd.FCondition;
import lx.gs.logger.By;
import lx.gs.map.FMap;
import lx.gs.map.msg.SCancelTeamSpeedApply;
import lx.gs.tips.FTips;
import cfg.tips.TipsCode;
import xdb.Trace;
import xdb.Transaction;
import xtable.Roleinfos;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 鸿蒙争霸
 * @author Jin Shuai
 */
public enum TeamSpeedModule implements gs.Module, RoleLoginListener {
    INSTANCE;

    private static final int STATE_CLOSE = 0;  // 关闭
    private static final int STATE_OPEN = 1;   // 开启
    private static final int STATE_APPLY = 2;  // 报名时间
    private static final int STATE_BATTLE = 3; // 战斗时间

    private AtomicInteger state = new AtomicInteger(STATE_CLOSE);

    protected final TeamSpeed cfg = CfgMgr.teamspeed;

    // 一轮时间
    private long roundInterval = TimeUnit.SECONDS.toMillis(cfg.battlelast);

    private ScheduledFuture<?> runningFuture = null;
    private ScheduledFuture<?> battleFuture = null;
    private ScheduledFuture<?> broadcastFuture = null;

    private Map<Integer, GroupWaitingList> playerWaitingList = new ConcurrentHashMap<>();

    protected final Set<Long> applyPlayers = new CopyOnWriteArraySet<>();

    protected final Map<Integer, Integer> levelGroupMap = new HashMap<>();

    @Override
    public void start() {
        init();
    }

    public void init(){
        for (SpeedLvMsg speedLvMsg : cfg.lvmsg) {
            for (int i = speedLvMsg.lv.min; i <= speedLvMsg.lv.max; i++) {
                levelGroupMap.put(i, speedLvMsg.id);
            }
        }

        List<DayTimeRange> timeInfo = cfg.timeinfo;
        long now = System.currentTimeMillis();
        for(DayTimeRange range : timeInfo) {
            if(Time.inDayTimeRange(range, now) && !isOpen()){
                long t1 = Time.getDayMilliseconds(now);
                long t2 = Time.calcDayMilliseconds(range.begintime);
                int roundIndex = (int) ((t1 - t2) / roundInterval);
                roundIndex++;
                long delay = roundInterval * roundIndex - t1 + t2;
                TaskQueue.getScheduleExecutor().schedule(() -> {
                    final List<SimpleDailyActivity.DailyWork> works = new ArrayList<>();
                    works.add(new SimpleDailyActivity.DailyWork(range.begintime, this::onOpen));
                    works.add(new SimpleDailyActivity.DailyWork(range.endtime, this::onClose));
                    ActivityModule.INSTACNE.addSimpleDailyActivity(works);
                }, delay, TimeUnit.MILLISECONDS);
                continue;
            }

            final List<SimpleDailyActivity.DailyWork> works = new ArrayList<>();
            works.add(new SimpleDailyActivity.DailyWork(range.begintime, this::onOpen));
            works.add(new SimpleDailyActivity.DailyWork(range.endtime, this::onClose));
            ActivityModule.INSTACNE.addSimpleDailyActivity(works);
        }

    }

    public boolean isOpen(){
        return state.get() != STATE_CLOSE;
    }

    public void open4Test(){
        startRound(1, TimeUnit.MINUTES);
    }

    private void onOpen() {
        Trace.info("***************************鸿蒙争霸，活动开启***************************");
        state.set(STATE_OPEN);
        runningFuture = TaskQueue.getScheduleExecutor().scheduleAtFixedRate(() -> {
            try{
                startRound(cfg.singuplast, TimeUnit.SECONDS);
            } catch (Exception e){
                Trace.error("teamspeed running exception,", e);
            }
        }, 0, roundInterval, TimeUnit.MILLISECONDS);
        FTips.broadcast(LocationType.CENTER, cfg.broadcastinfo.openbroadcast.broadcastdec);
        int interval = cfg.broadcastinfo.ongoingterminal;
        broadcastFuture = TaskQueue.getScheduleExecutor().scheduleAtFixedRate(this::broadcastTips, interval, interval, TimeUnit.SECONDS);
    }

    private void broadcastTips(){
        FTips.broadcast(LocationType.CENTER, cfg.broadcastinfo.ongoingbroadcast.broadcastdec);
    }

    private void startRound(int applyTime, TimeUnit unit){
        Trace.info("***************************鸿蒙争霸，开始报名***************************");
        state.set(STATE_APPLY);
        applyPlayers.clear();
        battleFuture = TaskQueue.getScheduleExecutor().schedule(this::startBattle, applyTime, unit);
    }

    private void startBattle(){
        Trace.info("***************************鸿蒙争霸，报名结束，进场战斗***************************");
        state.set(STATE_BATTLE);
        try {
            playerWaitingList.values().forEach(GroupWaitingList::doMatch);
        }catch (Exception e){
            Trace.error("teamspeed match error", e);
        }
    }

    private void onClose() {
        Trace.info("***************************鸿蒙争霸，活动关闭***************************");
        state.set(STATE_CLOSE);
        playerWaitingList.values().forEach(GroupWaitingList::clear);
        applyPlayers.clear();
        if(runningFuture != null){
            runningFuture.cancel(false);
            runningFuture = null;
        }
        if(battleFuture != null){
            battleFuture.cancel(false);
            battleFuture = null;
        }
        if(broadcastFuture != null){
            broadcastFuture.cancel(false);
            broadcastFuture = null;
        }
        FTips.broadcast(LocationType.CENTER, cfg.broadcastinfo.endbroadcast.broadcastdec);
    }

    public boolean applyMatch(long applyRoleId, List<Long> roleIdList){
        if(!isApplyTime()){
            Transaction.tsend(applyRoleId, new SError(ErrorCode.TEAMSPEED_NOT_APPLY_TIME.getErrorId()));
            return false;
        }
        if(roleIdList.size() > cfg.teamernumber || roleIdList.isEmpty()){
            Transaction.tsend(applyRoleId, new SError(ErrorCode.TEAMSPEED_MEMBERS_ERROR.getErrorId()));
            return false;
        }

        int groupIndex = levelGroupMap.getOrDefault(Roleinfos.selectLevel(roleIdList.get(0)), Const.NULL);
        if(groupIndex < 0)
            return false;

        for (long roleId : roleIdList) {
            if(applyPlayers.contains(roleId)){
                Transaction.tsend(applyRoleId, FTips.create(LocationType.ALERT, TipsCode.TEAM_MEMBER_ALREADY_APPLY, Roleinfos.selectName(roleId)));
                return false;
            }

            if(levelGroupMap.get(Roleinfos.selectLevel(roleId)) != groupIndex){
                Transaction.tsend(applyRoleId, new SError(ErrorCode.TEAMSPEED_MEMBERS_NOT_IN_SAME_SCOPE.getErrorId()));
                return false;
            }

            final RefObject<Boolean> check = new RefObject<>(false);
            new xdb.Procedure() {
                @Override
                protected boolean process() {
                    check.value = FCondition.checkA(roleId, CfgMgr.teamspeed.dailylimit, 1, By.Team_Speed_Reward, ConfigId.TEAM_SPEED, 0).ok();
                    return false;
                }
            }.call();
            if(!check.value) {
                Transaction.tsend(applyRoleId, FTips.create(LocationType.ALERT, TipsCode.EXCEED_LIMIT, Roleinfos.selectName(roleId)));
                return false;
            }
        }

        roleIdList.forEach(roleId -> FMap.getEctype(roleId).setMatchtype(MatchType.TEAM_SPEED));

        if(!playerWaitingList.containsKey(groupIndex)){
            playerWaitingList.putIfAbsent(groupIndex, new GroupWaitingList());
        }
        applyPlayers.addAll(roleIdList);
        return playerWaitingList.get(groupIndex).offer(roleIdList);
    }

    public boolean cancelApply(long roleId) {
        if(applyPlayers.contains(roleId)){
            applyPlayers.remove(roleId);
            playerWaitingList.get(levelGroupMap.get(Roleinfos.selectLevel(roleId))).cancelApply(roleId);
            FMap.getEctype(roleId).setMatchtype(0);
            Transaction.tsendWhileCommit(roleId, new SCancelTeamSpeedApply());
            return true;
        }
        return false;
    }

    public boolean isApplyTime() {
        return state.get() == STATE_APPLY;
    }

    @Override
    public void afterRoleLoginInProcedure(long roleid) {
    }

    @Override
    public void beforeRoleLogoutInProcedure(long roleid) {
        cancelApply(roleid);
    }
}
