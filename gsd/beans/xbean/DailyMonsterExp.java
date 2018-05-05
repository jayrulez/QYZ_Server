
package xbean;

public interface DailyMonsterExp extends xdb.Bean {
	public DailyMonsterExp copy(); // deep clone
	public DailyMonsterExp toData(); // a Data instance
	public DailyMonsterExp toBean(); // a Bean instance
	public DailyMonsterExp toDataIf(); // a Data instance If need. else return this
	public DailyMonsterExp toBeanIf(); // a Bean instance If need. else return this

	public long getTodaytotaladdmonsterexp(); // 

	public void setTodaytotaladdmonsterexp(long _v_); // 
}
