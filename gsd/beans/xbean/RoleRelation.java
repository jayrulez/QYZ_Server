
package xbean;

public interface RoleRelation extends xdb.Bean {
	public RoleRelation copy(); // deep clone
	public RoleRelation toData(); // a Data instance
	public RoleRelation toBean(); // a Bean instance
	public RoleRelation toDataIf(); // a Data instance If need. else return this
	public RoleRelation toBeanIf(); // a Bean instance If need. else return this

	public java.util.List<Long> getRolelist(); // 
	public java.util.List<Long> getRolelistAsData(); // 

}
