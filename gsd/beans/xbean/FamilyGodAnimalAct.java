
package xbean;

public interface FamilyGodAnimalAct extends xdb.Bean {
	public FamilyGodAnimalAct copy(); // deep clone
	public FamilyGodAnimalAct toData(); // a Data instance
	public FamilyGodAnimalAct toBean(); // a Bean instance
	public FamilyGodAnimalAct toDataIf(); // a Data instance If need. else return this
	public FamilyGodAnimalAct toBeanIf(); // a Bean instance If need. else return this

	public long getLastlaunchtime(); // 最近一次开启的时间
	public long getStarttime(); // 活动开始时间
	public long getEndtime(); // 活动结束时间
	public int getWeeklaunchnum(); // 每周开启的次数

	public void setLastlaunchtime(long _v_); // 最近一次开启的时间
	public void setStarttime(long _v_); // 活动开始时间
	public void setEndtime(long _v_); // 活动结束时间
	public void setWeeklaunchnum(int _v_); // 每周开启的次数
}
