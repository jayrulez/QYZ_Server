package lx.gs.achievement;

import cfg.CfgMgr;
import cfg.achievement.Achievement;
import cfg.achievement.CounterType;
import gs.AProcedure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public enum AchievementModule implements gs.Module, gs.RoleLoginListener, gs.LevelUpListener {
	INSTANCE;

	@Override
	public void start() {
		groupAndSortAchievements();
	}
	
	private void groupAndSortAchievements() {
		for(cfg.achievement.Achievement a : CfgMgr.achievement.values()) {
			List<cfg.achievement.Achievement> as = achievementsByType.get(a.type);
			if(as == null) {
				as = new ArrayList<>();
				achievementsByType.put(a.type, as);
			}
			as.add(a);
		}
		
		for(List<cfg.achievement.Achievement> as : achievementsByType.values()) {
			Collections.sort(as, (o1, o2) -> (o1.value - o2.value) > 0 ? 1 : -1);
		}
	}

	public List<Achievement> getAchievementsByType(int type) {
		return achievementsByType.getOrDefault(type, Collections.emptyList());
	}
	
	private final HashMap<Integer, List<cfg.achievement.Achievement>> achievementsByType = new HashMap<>();
	
	@Override
	public void afterRoleLoginInProcedure(long roleid) {
		FAchievement.initCounters(roleid);
		AProcedure.tsend(roleid, FAchievement.createSInfo(roleid));
	}

	@Override
	public void beforeRoleLogoutInProcedure(long roleid) {

	}

	@Override
	public void onLevelUp(long roleid, int oldLevel, int newLevel) {
		FAchievement.updateCounter(roleid, cfg.achievement.CounterType.LEVEL, newLevel);
	}

	public void onRoleCreateInProcedure(long roleid, xbean.RoleInfo roleInfo) {
		FAchievement.updateCounter(roleid, CounterType.LEVEL, roleInfo.getLevel());
		FAchievement.updateCounter(roleid, CounterType.CONTINUE_LOGIN, roleInfo.getContinuesloginday());
	}

}
