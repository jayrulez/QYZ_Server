package lx.gs.achievement;

import cfg.CfgMgr;
import cfg.achievement.Achievement;
import gs.AProcedure;
import lx.gs.achievement.msg.SComplete;
import lx.gs.achievement.msg.SCounterChange;
import lx.gs.achievement.msg.SInfo;
import lx.gs.cmd.FAction;
import lx.gs.cmd.FCmd;
import lx.gs.cmd.FCondition;
import common.ErrorCode;
import lx.gs.logger.By;
import lx.gs.role.title.FTitle;

import java.util.List;
import java.util.Map;

public final class FAchievement {
	
	public static xbean.RoleAchievement get(long roleid) {
		xbean.RoleAchievement info = xtable.Roleachievement.get(roleid);
		if(info == null) {
			info = xbean.Pod.newRoleAchievement();
			xtable.Roleachievement.insert(roleid, info);
		}
		return info;
	}
	
	static List<Achievement> getAchievementsByType(int type) {
		return AchievementModule.INSTANCE.getAchievementsByType(type);
	}

	static void initCounters(long roleid) {
		final xbean.RoleAchievement info = get(roleid);
		final Map<Integer, Integer> completes = info.getAchievementstates();

		// 可能会添加一些新的成就.故登陆时刷一遍
		for(Map.Entry<Integer, Long> e : info.getCounters().entrySet()) {
			checkCompleteCount(roleid, completes, e.getKey(), e.getValue());
		}
		for(Map.Entry<Integer, xbean.CounterSet> e : info.getCountersets().entrySet()) {
			checkCompleteCount(roleid, completes, e.getKey(), e.getValue().getItems().size());
		}
	}
	
	public static void updateCounter(long roleid, int type, long value) {
		final xbean.RoleAchievement info = get(roleid);
		final Map<Integer, Long> counters = info.getCounters();
		if(counters.getOrDefault(type, 0L) < value) {
			counters.put(type, value);
			checkCompleteCount(roleid, info.getAchievementstates(), type, value);
			AProcedure.tsend(roleid, new SCounterChange(type, value));
		}
	}
	
	public static void addCounter(long roleid, int type, long add) {
		final xbean.RoleAchievement info = get(roleid);
		final Map<Integer, Long> counters = info.getCounters();
		final long value = counters.getOrDefault(type, 0L) + add;
		counters.put(type, value);
		checkCompleteCount(roleid, info.getAchievementstates(), type, value);
		AProcedure.tsend(roleid, new SCounterChange(type, value));
	}
	
	public static void checkCompleteEqual(long roleid, int type, long value) {
		checkCompleteEqual(roleid, get(roleid).getAchievementstates(), type, value);
	}
	
	private static void checkCompleteCount(long roleid, Map<Integer, Integer> completes, int type, long value) {
		for(cfg.achievement.Achievement a : getAchievementsByType(type)) {
			if(a.value <= value) {
				if(!completes.containsKey(a.id)) {
					completes.put(a.id, cfg.achievement.Status.COMPLETED);
					AProcedure.tsend(roleid, new SComplete(a.id));
				}
			} else {
				break;
			}
		}
	}
	
	private static void checkCompleteEqual(long roleid, Map<Integer, Integer> completes, int type, long value) {
		for(cfg.achievement.Achievement a : getAchievementsByType(type)) {
			if(a.value == value) {
				if(!completes.containsKey(a.id)) {
					completes.put(a.id, cfg.achievement.Status.COMPLETED);
					AProcedure.tsend(roleid, new SComplete(a.id));
				}
				break;
			}
		}
	}
	
	public static ErrorCode getReward(long roleid, int achievementid) {
		final xbean.RoleAchievement info = get(roleid);
		final Map<Integer, Integer> completes = info.getAchievementstates();
		final int status = completes.getOrDefault(achievementid, cfg.achievement.Status.NOTCOMPLETED);
		switch(status) {
			case cfg.achievement.Status.NOTCOMPLETED: return ErrorCode.ACHIEVEMENT_NOT_COMPLETED;
			case cfg.achievement.Status.GETREWARD: return ErrorCode.ACHIEVEMENT_GET_REWARD;
		}
		completes.put(achievementid, cfg.achievement.Status.GETREWARD);
		final FCmd.Context ctx = FAction.processByReflection(roleid, CfgMgr.achievement.get(achievementid), By.Achievement_Reward);
		return ctx.errcode;
	}
	
	public static ErrorCode evolveTitle(long roleid, int titleid) {
		final cfg.achievement.TitleEvolve ecfg = CfgMgr.titleevolve.get(titleid);
		if(ecfg == null)
			return ErrorCode.ACHIEVEMENT_INVALID_EVOLVE_TITLE_ID;
		final ErrorCode err = FCondition.checkByReflection(roleid, ecfg, By.Achievement_Evolve);
		if(err.err())
			return err;
		return FTitle.unlockTitle(roleid, titleid);
	}
	
	public static xio.Protocol createSInfo(long roleid) {
		final SInfo re = new SInfo();
		final xbean.RoleAchievement info = get(roleid);
		re.achievementstates.putAll(info.getAchievementstates());
		re.counters.putAll(info.getCounters());
		info.getCountersets().entrySet().forEach(e -> re.counters.put(e.getKey(), (long) e.getValue().getItems().size()));
		return re;
	}
}
