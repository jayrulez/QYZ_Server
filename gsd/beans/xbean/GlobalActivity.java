
package xbean;

public interface GlobalActivity extends xdb.Bean {
	public GlobalActivity copy(); // deep clone
	public GlobalActivity toData(); // a Data instance
	public GlobalActivity toBean(); // a Bean instance
	public GlobalActivity toDataIf(); // a Data instance If need. else return this
	public GlobalActivity toBeanIf(); // a Bean instance If need. else return this

	public long getTimestamp(); // 

	public void setTimestamp(long _v_); // 
}
