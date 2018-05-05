
package xbean;

public interface UserDeletedRole extends xdb.Bean {
	public UserDeletedRole copy(); // deep clone
	public UserDeletedRole toData(); // a Data instance
	public UserDeletedRole toBean(); // a Bean instance
	public UserDeletedRole toDataIf(); // a Data instance If need. else return this
	public UserDeletedRole toBeanIf(); // a Bean instance If need. else return this

	public java.util.List<xbean.DeletedRole> getRoles(); // 
	public java.util.List<xbean.DeletedRole> getRolesAsData(); // 

}
