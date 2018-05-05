
package xbean;

public interface AppOrder extends xdb.Bean {
	public AppOrder copy(); // deep clone
	public AppOrder toData(); // a Data instance
	public AppOrder toBean(); // a Bean instance
	public AppOrder toDataIf(); // a Data instance If need. else return this
	public AppOrder toBeanIf(); // a Bean instance If need. else return this

	public long getRoleid(); // 
	public int getProductid(); // 
	public long getTime(); // 

	public void setRoleid(long _v_); // 
	public void setProductid(int _v_); // 
	public void setTime(long _v_); // 
}
