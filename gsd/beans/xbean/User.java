
package xbean;

public interface User extends xdb.Bean {
	public User copy(); // deep clone
	public User toData(); // a Data instance
	public User toBean(); // a Bean instance
	public User toDataIf(); // a Data instance If need. else return this
	public User toBeanIf(); // a Bean instance If need. else return this

	public java.util.List<Long> getRoleids(); // 
	public java.util.List<Long> getRoleidsAsData(); // 
	public long getLastloginrole(); // 
	public int getDeletedroles(); // 
	public java.util.Map<Long, Long> getDeleteinfo(); // 
	public java.util.Map<Long, Long> getDeleteinfoAsData(); // 

	public void setLastloginrole(long _v_); // 
	public void setDeletedroles(int _v_); // 
}
