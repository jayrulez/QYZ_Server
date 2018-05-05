
package xbean;

public interface RoleActiveCode extends xdb.Bean {
	public RoleActiveCode copy(); // deep clone
	public RoleActiveCode toData(); // a Data instance
	public RoleActiveCode toBean(); // a Bean instance
	public RoleActiveCode toDataIf(); // a Data instance If need. else return this
	public RoleActiveCode toBeanIf(); // a Bean instance If need. else return this

	public java.util.Map<Integer, xbean.ActiveCode> getCodegroups(); // 
	public java.util.Map<Integer, xbean.ActiveCode> getCodegroupsAsData(); // 

}
