package lx.gs.map.guardtower;

import cfg.CfgMgr;
import cfg.DataStream;
import cfg.common.DayTime;
import cfg.ectype.GuardTower;
import cfg.tips.LocationType;
import common.TaskQueue;
import gnet.ServiceClient;
import lx.gs.activity.ActivityModule;
import lx.gs.activity.SimpleDailyActivity;
import lx.gs.map.FMap;
import lx.gs.tips.FTips;
import lx.matcher.guardtower.GAddGuardTowerMatch;
import lx.matcher.guardtower.GCancelGuardTowerMatch;
import match.PVELevelCombatPowerMatcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by live106 on 2016/5/12.
 */
public enum GuardTowerModule implements gs.Module {
    INSTANCE;

    public static int matchMemberNum = 3;
    public static int maxWave = Integer.MAX_VALUE;
    private ScheduledFuture<?> broadcastFuture = null;
    private GuardTower cfg = CfgMgr.guardtower;
    //不用来匹配，用来检测条件
    private GuardTowerMatcher matcher;

    @Override
    public void start() {
        final PVELevelCombatPowerMatcher.Builder builder = new PVELevelCombatPowerMatcher.Builder();
        builder.roundInterval = 5000L;
        builder.matchTimeout = CfgMgr.guardtower.matchtimeout * 1000L;
        builder.teamMemberNum = matchMemberNum;
        builder.levelRangeSplit = new ArrayList<>();
        CfgMgr.guardtower.zones.forEach(z -> builder.levelRangeSplit.add(z.levellimit.max));
        matcher = new GuardTowerMatcher(builder);

        DayTime open = new DayTime(new DataStream(
                Arrays.asList(String.valueOf(cfg.opentime.hour), String.valueOf(cfg.opentime.minute), "0")));
        long endSeconds = TimeUnit.HOURS.toSeconds(cfg.opentime.hour) + TimeUnit.MINUTES.toSeconds(cfg.opentime.minute) + cfg.last;
        DayTime end = new DayTime(new DataStream(
                Arrays.asList(String.valueOf(endSeconds / TimeUnit.HOURS.toSeconds(1)), String.valueOf((endSeconds % TimeUnit.HOURS.toSeconds(1)) / TimeUnit.MINUTES.toSeconds(1)), "0")));
        final List<SimpleDailyActivity.DailyWork> works = new ArrayList<>();
        works.add(new SimpleDailyActivity.DailyWork(open, this::onOpen));
        works.add(new SimpleDailyActivity.DailyWork(end, this::onClose));
        ActivityModule.INSTACNE.addSimpleDailyActivity(works);
    }

    private void onOpen() {
        int interval = cfg.broadcastinfo.ongoingterminal;
        broadcastFuture = TaskQueue.getScheduleExecutor().scheduleAtFixedRate(this::broadcastTips, interval, interval, TimeUnit.SECONDS);
        FTips.broadcast(LocationType.CENTER, cfg.broadcastinfo.openbroadcast.broadcastdec);
    }

    private void broadcastTips(){
        FTips.broadcast(LocationType.CENTER, cfg.broadcastinfo.ongoingbroadcast.broadcastdec);
    }

    private void onClose() {
        broadcastFuture.cancel(false);
        FTips.broadcast(LocationType.CENTER, cfg.broadcastinfo.endbroadcast.broadcastdec);
    }

    public void addMatch(int gid, List<Long> members, int minPower, int selfPower) {
        ServiceClient.send(new GAddGuardTowerMatch(gid, minPower, selfPower, FMap.genTeamInfo(members)));
    }

    public void removeTeamByRoleid(long roleid) {
        ServiceClient.send(new GCancelGuardTowerMatch(roleid));
    }

    public GuardTowerMatcher getMatcher() {
        return matcher;
    }
}
