
package xbean;

public interface TimeRange extends xdb.Bean {
	public TimeRange copy(); // deep clone
	public TimeRange toData(); // a Data instance
	public TimeRange toBean(); // a Bean instance
	public TimeRange toDataIf(); // a Data instance If need. else return this
	public TimeRange toBeanIf(); // a Bean instance If need. else return this

	public long getOpentime(); // 
	public long getClosetime(); // 

	public void setOpentime(long _v_); // 
	public void setClosetime(long _v_); // 
}
