package lx.gs.login;

import cfg.achievement.CounterType;
import lx.gs.achievement.FAchievement;

public final class FLogin {
	public static long calcTodayOnlineTime(xbean.RoleInfo info) {
		return calcTodayOnlineTime(info, System.currentTimeMillis());
	}
	
	public static long calcTodayOnlineTime(xbean.RoleInfo info, long now) {
		final long lastLoginTime = info.getLastlogintime();
		if(!common.Time.isSameDay(lastLoginTime, now)) {
			return now - common.Time.todayZeroTime(now);
		} else {
			return info.getTotaldayonlinetime() + now - lastLoginTime;
		}
	}

	public static void onContinueLoginDayChange(long roleid, int day) {
		FAchievement.updateCounter(roleid, CounterType.CONTINUE_LOGIN, day);
	}

	public static String makeFullName(String name, int serverid) {
		return name + "@" + serverid;
	}
}
