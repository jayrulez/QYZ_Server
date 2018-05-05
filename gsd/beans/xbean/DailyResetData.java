
package xbean;

public interface DailyResetData extends xdb.Bean {
	public DailyResetData copy(); // deep clone
	public DailyResetData toData(); // a Data instance
	public DailyResetData toBean(); // a Bean instance
	public DailyResetData toDataIf(); // a Data instance If need. else return this
	public DailyResetData toBeanIf(); // a Bean instance If need. else return this

	public long getLastupdatedailytime(); // 
	public xbean.DailyArena getArena(); // 
	public xbean.DailyMonsterExp getMonsterexp(); // 
	public int getKillexpmonsters(); // 击杀经验怪的数量
	public java.util.List<Integer> getRecexpmonbonus(); // 已经领取的击杀经验怪奖励
	public java.util.List<Integer> getRecexpmonbonusAsData(); // 已经领取的击杀经验怪奖励

	public void setLastupdatedailytime(long _v_); // 
	public void setKillexpmonsters(int _v_); // 击杀经验怪的数量
}
