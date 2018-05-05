
package xbean;

public interface RoleActivityStatus extends xdb.Bean {
	public RoleActivityStatus copy(); // deep clone
	public RoleActivityStatus toData(); // a Data instance
	public RoleActivityStatus toBean(); // a Bean instance
	public RoleActivityStatus toDataIf(); // a Data instance If need. else return this
	public RoleActivityStatus toBeanIf(); // a Bean instance If need. else return this

	public long getTimestamp(); // 
	public int getStatus(); // 

	public void setTimestamp(long _v_); // 
	public void setStatus(int _v_); // 
}
