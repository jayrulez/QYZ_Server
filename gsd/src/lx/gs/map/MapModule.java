package lx.gs.map;

import cfg.CfgMgr;
import cfg.ectype.DailyEctype;
import cfg.ectype.EctypeType;
import cfg.ectype.TeamStoryEctype;
import common.TaskQueue;
import lx.gs.map.msg.SChangeTeamFight;
import match.Manager;
import xdb.Trace;
import xdb.Xdb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public enum MapModule implements gs.Module, gs.GsdStartListener, gs.RoleLoginListener, gs.RoleDayOverListener {
	INSTANCE;

	public final static ConcurrentHashMap<Long, RoleContext> players = new ConcurrentHashMap<>();

	public final static HashMap<Integer, Integer> storyEctypeid2PrevEctypeid = new HashMap<>(); // 求出每个剧情副本的前置副本
	public final static HashMap<Integer, List<cfg.ectype.StoryEctype>> chapterEctypes = new HashMap<>();

	public final static HashMap<Integer, List<DailyEctype>> dailyEctypes = new HashMap<>();

    public final static int ARENA_ECTYPE_ID = CfgMgr.ectypebasic.values().stream().filter(e -> e.type == EctypeType.ARENA).findFirst().get().id;
    public final static int PROLOGUE_ECTYPE_ID = CfgMgr.ectypebasic.values().stream().filter(e -> e.type == EctypeType.PROLOGUE).findFirst().get().id;
    public final static int CLIMB_TOWER_ECTYPE_ID = CfgMgr.ectypebasic.values().stream().filter(e -> e.type == EctypeType.CLIMB_TOWER).findFirst().get().id;
    //多人剧情副本匹配管理器
    public final static int MULTI_STORY_MATCH_NUMS = 3;


	@Override
	public void afterRoleLoginInProcedure(long roleid) {
		if(!players.contains(roleid)) {
			Trace.info("MapModule. createPlayerInfo in rolelogin  roleid:{}", roleid);
			players.putIfAbsent(roleid, new RoleContext(roleid).load(FMap.get(roleid)));
		}
		FMap.refreshAndSendEctypeInfo(roleid);
	}

	@Override
	public void beforeRoleLogoutInProcedure(long roleid) {
        FMap.checkCancelMatch(roleid);
        FMap.addLockOper(roleid, (op, param) -> {
            op.getRoleMapInfo().setOffline(true);
            return RoleContext.Result.END;
        });
	}

    @Override
    public void onDayOver(long roleid) {
        new xdb.Procedure() {
            @Override
            protected boolean process() {
                final xbean.RoleEctype info = FMap.getEctype(roleid);
                FMap.refreshEctypeInfo(info);
                xdb.Transaction.tsendWhileCommit(roleid, new SChangeTeamFight(FMap.convert(info.getTeamfight())));
                return true;
            }
        }.execute();
    }


    public enum MapState {
		FAILED,
		CREATING,
		NORMAL,
		DESTROYING,
		DESTROYED,
	}
	public final static class MapInfo {
        public final int worldid;
        public final int lineid;
		public long mapid;
		public MapState state;
		public volatile int playerNum;
        public MapInfo(int worldid, int lineid) {
            this.worldid = worldid;
            this.lineid = lineid;
        }
	}

	public final static HashMap<Integer, Map<Integer, MapInfo>> worldsByLines = new HashMap<>();

	public static int createWorldAtServerid;

    private void prepareConf() {
        // 强制要求策划配置副本必须按照章节顺序
        // 正常情况下策划是这么配的,并没有带来额外负担
        // 但让程序的检查代码好写很多
        int prevEctypeid = 0;
        int chapterid = 0;
        int sectionid = 0;
        List<cfg.ectype.StoryEctype> storys = null;
        for(cfg.ectype.StoryEctype scfg : CfgMgr.storyectype.values()) {
            storyEctypeid2PrevEctypeid.put(scfg.id, prevEctypeid);
            prevEctypeid = scfg.id;
            if(scfg.chapter != chapterid) {
                assert(chapterid + 1 == scfg.chapter);
                sectionid = 0;
                chapterid = scfg.chapter;
                storys = new ArrayList<>();
                chapterEctypes.put(chapterid, storys);
            }
            assert(sectionid + 1 == scfg.section);
            sectionid = scfg.section;
            storys.add(scfg);
        }

        int etype = -1;
        List<cfg.ectype.DailyEctype> dailys = null;
        for(cfg.ectype.DailyEctype dcfg : CfgMgr.dailyectype.values()) {
            final cfg.ectype.EctypeBasic ecfg = CfgMgr.ectypebasic.get(dcfg.id);
            if(ecfg.type != etype) {
                etype = ecfg.type;
                dailys = new ArrayList<>();
                dailyEctypes.put(etype, dailys);
            }
            dailys.add(dcfg);
        }
    }

	@Override
	public void start() {
        prepareConf();
        map.MapModule.INSTANCE.start();

	}

	@Override
	public void afterGsdStart() {
	    createWorldAtServerid = gs.Utils.getServerId();
        //TaskQueue.getScheduleExecutor().schedule(() -> {
            for (cfg.map.WorldMap wcfg : CfgMgr.worldmap.values()) {
                worldsByLines.put(wcfg.id, new ConcurrentHashMap<>());
                for (int lineid = 1; lineid <= wcfg.initlinenum; lineid++) {
                    FMap.createWorldMapNotInProcedure(wcfg.id, lineid);
                }
            }
        //}, 5, TimeUnit.SECONDS);
	}

	@Override
	public void beforeGsdStop() {

	}


}
