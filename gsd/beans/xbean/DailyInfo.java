
package xbean;

public interface DailyInfo extends xdb.Bean {
	public DailyInfo copy(); // deep clone
	public DailyInfo toData(); // a Data instance
	public DailyInfo toBean(); // a Bean instance
	public DailyInfo toDataIf(); // a Data instance If need. else return this
	public DailyInfo toBeanIf(); // a Bean instance If need. else return this

	public int getValue(); // 

	public void setValue(int _v_); // 
}
