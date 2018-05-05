
package xbean;

public interface DeletedRole extends xdb.Bean {
	public DeletedRole copy(); // deep clone
	public DeletedRole toData(); // a Data instance
	public DeletedRole toBean(); // a Bean instance
	public DeletedRole toDataIf(); // a Data instance If need. else return this
	public DeletedRole toBeanIf(); // a Bean instance If need. else return this

	public long getRoleid(); // 
	public String getUsername(); // 
	public com.goldhuman.Common.Octets getUsernameOctets(); // 
	public String getNewusername(); // 
	public com.goldhuman.Common.Octets getNewusernameOctets(); // 
	public long getDeletetime(); // 

	public void setRoleid(long _v_); // 
	public void setUsername(String _v_); // 
	public void setUsernameOctets(com.goldhuman.Common.Octets _v_); // 
	public void setNewusername(String _v_); // 
	public void setNewusernameOctets(com.goldhuman.Common.Octets _v_); // 
	public void setDeletetime(long _v_); // 
}
