package lx.gs.map;

import cfg.CfgMgr;
import cfg.ectype.TeamFightLevelGroup;
import cfg.ectype.TeamFightStage;
import cfg.tips.LocationType;
import common.TaskQueue;
import gnet.ServiceClient;
import lx.gs.activity.ActivityModule;
import lx.gs.activity.SimpleDailyActivity;
import lx.gs.tips.FTips;
import lx.matcher.GAddTeamFightMatch;
import lx.matcher.GCancelTeamFightMatch;
import lx.matcher.GEndTeamFight;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by HuangQiang on 2016/5/19.
 */
public enum TeamFightModule implements gs.Module {
    INSTANCE;

    public volatile int stage;
    private ScheduledFuture<?> broadcastFuture = null;

    @Override
    public void start() {
        stage = TeamFightStage.CLOSED;

        final cfg.ectype.TeamFight acfg = CfgMgr.teamfight;
        for(cfg.common.DayTimeRange range : acfg.opentimes) {
            final List<SimpleDailyActivity.DailyWork> works = new ArrayList<>();
            works.add(new SimpleDailyActivity.DailyWork(range.begintime, this::onOpen));
            works.add(new SimpleDailyActivity.DailyWork(range.endtime, this::onClose));
            ActivityModule.INSTACNE.addSimpleDailyActivity(works);
        }
    }

    public void open() {
        if(stage != TeamFightStage.OPEN)
            onOpen();
    }

    private void onOpen() {
        stage = TeamFightStage.OPEN;
        int interval = CfgMgr.teamfight.broadcastinfo.ongoingterminal;
        broadcastFuture = TaskQueue.getScheduleExecutor().scheduleAtFixedRate(this::broadcastTips, interval, interval, TimeUnit.SECONDS);
        FTips.broadcast(LocationType.CENTER, CfgMgr.teamfight.broadcastinfo.openbroadcast.broadcastdec);
    }

    private void broadcastTips(){
        FTips.broadcast(LocationType.CENTER, CfgMgr.teamfight.broadcastinfo.ongoingbroadcast.broadcastdec);
    }

    private void onClose() {
        stage = TeamFightStage.CLOSED;
        TaskQueue.getScheduleExecutor().schedule(() -> {
          ServiceClient.send(new GEndTeamFight());
        }, 5, TimeUnit.SECONDS);
        broadcastFuture.cancel(false);
        FTips.broadcast(LocationType.CENTER, CfgMgr.teamfight.broadcastinfo.endbroadcast.broadcastdec);
    }

    public int calcGroupid(int level) {
        int gid = 0;
        for(TeamFightLevelGroup g : CfgMgr.teamfight.levelgroups) {
            if(g.level > level)
                return gid;
            ++gid;
        }
        return gid - 1;
    }

    public void addMatch(long gid, List<Long> members, int minVipLevel) {
        ServiceClient.send(new GAddTeamFightMatch(gid, minVipLevel, FMap.genTeamInfo(members)));
    }

    public void removeTeamByRoleid(long roleid) {
        ServiceClient.send(new GCancelTeamFightMatch(roleid));
    }
}
