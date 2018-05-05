package lx.gs.gm;

import cfg.CfgMgr;
import gm.GmSession;
import gm.annotation.Cmd;
import gm.annotation.Module;
import gm.annotation.Param;
import gs.Listeners;
import lx.gs.activity.ActivityModule;
import lx.gs.activity.huiwu.HuiWu;
import lx.gs.activity.worldmonster.msg.CGetExpMonsterPosition;
import lx.gs.dailyactivity.FDailyActivity;
import lx.gs.map.FMap;
import map.msg.XCreateHuiWu;

@Module(comment="日常活动GM命令")
public class Activity {
	@Cmd(comment="设置某个系统未完成的次数")
	public Object setUndoInfo(@Param(name="roleid",comment="角色id：roleid")long roleid, @Param(name="type",comment="系统类型")int type,@Param(name="times",comment="未完成次数")int times){
		xbean.DailyActive activeInfo = FDailyActivity.getDailyBean(roleid);
		if(!activeInfo.getUndoactive().containsKey(type)){
			return "该类型不存在";
		}
		if(times > cfg.CfgMgr.activeevent.get(type).times){
			return "未完成次数大于配置的最大次数";
		}
		activeInfo.getUndoactive().put(type, times);
		return "当前类型为: "+ type + " 的系统未完成次数为: " + times ;
	}
	
	@Cmd(comment="给某个系统完成次数增加1")
	public Object setDoneInfo(@Param(name="roleid",comment="角色id：roleid")long roleid, @Param(name="type",comment="系统类型")int type){
		FDailyActivity.addActiveScores(roleid, type);
		return "当前类型为: "+ type + " 的系统完成次数为: " + FDailyActivity.getEventTime(roleid, type);
	}

    @Cmd(comment="查询活跃系统和找回系统的数据")
    public Object queryActiveEvent(){
        final long roleid = GmSession.current().getRoleid();
        return FDailyActivity.getDailyBean(roleid);
    }

//	@Cmd(comment="提前开启世界boss")
//	public void openAheadWorldBoss(@Param(name="bossid", comment = "世界bossid") int bossid) {
//		ActivityModule.INSTACNE.addTask(() -> {
//			final WorldBoss boss = WorldBoss.getWorldBoss(bossid);
//			boss.openAhead(System.currentTimeMillis());
//		});
//	}
//
//	@Cmd(comment = "提前关闭世界boss")
//	public void closeAheadWorldBoss(@Param(name="bossid", comment = "世界bossid") int bossid) {
//		ActivityModule.INSTACNE.addTask(() -> {
//			final WorldBoss boss = WorldBoss.getWorldBoss(bossid);
//			boss.closeAhead(System.currentTimeMillis());
//		});
//	}

    @Cmd(comment = "设置七脉会武冠军")
    public Object setHuiwuChampion(@Param(name="roleid", comment = "角色id") long roleid) {
        ActivityModule.huiwuChampinionNotifys.put(xtable.Roleinfos.selectProfession(roleid), new ActivityModule.HuiWuChapinionNotifyRecord(roleid));
        return "succ";
    }

	@Cmd(comment = "设置七脉会武阶段")
	public Object setHuiWuStage(@Param(name="stage", comment = "七脉会武阶段 cfg.huiwu.Stage") int stage) {
		ActivityModule.INSTACNE.addTask(() -> {
			final HuiWu huiwu = HuiWu.Instance;
			huiwu.setStage(stage);
		});
        return "succ";
	}

    @Cmd(comment = "设置七脉会武回合阶段")
    public Object setHuiWuRoundStage(@Param(name="stage", comment = "七脉会武阶段 cfg.huiwu.RoundStage") int stage) {
        ActivityModule.INSTACNE.addTask(() -> {
            final HuiWu huiwu = HuiWu.Instance;
            huiwu.setRoundStage(stage);
        });
        return "succ";
    }

    @Cmd(comment = "进入 七脉会武 副本")
    public Object openHuiWu(@Param(name="opponentid", comment = "对手roleid") long opponentid) {
        final long roleid = GmSession.current().getRoleid();
        final XCreateHuiWu builder = new XCreateHuiWu();
        builder.ectypeid = CfgMgr.huiwu.ectypeid;
        builder.serverid = gs.Utils.getServerId();
        builder.roundindex = 1;
        builder.battleindex = 0;
        builder.player1 = FMap.createFakePlayerBuilder(roleid, 0);
        builder.player2 = FMap.createFakePlayerBuilder(opponentid, 0);

        FMap.createMapInProcedure(gs.Utils.getServerId(), builder, new FMap.CreateMapCallback<XCreateHuiWu>() {
            @Override
            public void onSucc(XCreateHuiWu builder, long mapid) {
                FMap.enterMapNotInProcedure(roleid, mapid);
            }
        });

        return "succ";
    }

    @Cmd(comment = "随机获取经验怪坐标")
    public Object getExpPosition(){
        gs.Utils.call(new CGetExpMonsterPosition());
        return "succ";
    }

    @Cmd(comment = "活跃值转建设度")
    public Object activetobuild(){
        final long roleid = GmSession.current().getRoleid();
        Listeners.INSTANCE.dayOver();
        return "succ";
    }
}
