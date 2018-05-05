
package xbean;

public interface RoleFragmentDepot extends xdb.Bean {
	public RoleFragmentDepot copy(); // deep clone
	public RoleFragmentDepot toData(); // a Data instance
	public RoleFragmentDepot toBean(); // a Bean instance
	public RoleFragmentDepot toDataIf(); // a Data instance If need. else return this
	public RoleFragmentDepot toBeanIf(); // a Bean instance If need. else return this

	public int getCapacity(); // 包裹容量 
	public java.util.Map<Integer, xbean.Fragment> getFragments(); // 背包里的碎片列表
	public java.util.Map<Integer, xbean.Fragment> getFragmentsAsData(); // 背包里的碎片列表

	public void setCapacity(int _v_); // 包裹容量 
}
