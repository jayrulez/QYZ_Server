
package xbean;

public interface DailyEctypeRecord extends xdb.Bean {
	public DailyEctypeRecord copy(); // deep clone
	public DailyEctypeRecord toData(); // a Data instance
	public DailyEctypeRecord toBean(); // a Bean instance
	public DailyEctypeRecord toDataIf(); // a Data instance If need. else return this
	public DailyEctypeRecord toBeanIf(); // a Bean instance If need. else return this

	public long getRoleid(); // 
	public int getValue(); // 

	public void setRoleid(long _v_); // 
	public void setValue(int _v_); // 
}
