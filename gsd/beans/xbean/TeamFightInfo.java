
package xbean;

public interface TeamFightInfo extends xdb.Bean {
	public TeamFightInfo copy(); // deep clone
	public TeamFightInfo toData(); // a Data instance
	public TeamFightInfo toBean(); // a Bean instance
	public TeamFightInfo toDataIf(); // a Data instance If need. else return this
	public TeamFightInfo toBeanIf(); // a Bean instance If need. else return this

	public long getLastupdatetime(); // 
	public int getWeekscore(); // 本周积分
	public int getTodaywinnum(); // 本日胜利次数
	public boolean getObtaintodaywinreward(); // 是否领过今日奖励
	public int getTodayfightnum(); // 本日参加次数
	public java.util.List<Integer> getObtainscorerewards(); // 本周已领积分奖励
	public java.util.List<Integer> getObtainscorerewardsAsData(); // 本周已领积分奖励

	public void setLastupdatetime(long _v_); // 
	public void setWeekscore(int _v_); // 本周积分
	public void setTodaywinnum(int _v_); // 本日胜利次数
	public void setObtaintodaywinreward(boolean _v_); // 是否领过今日奖励
	public void setTodayfightnum(int _v_); // 本日参加次数
}
