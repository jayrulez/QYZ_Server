
package xbean;

public interface RoleFragmentBag extends xdb.Bean {
	public RoleFragmentBag copy(); // deep clone
	public RoleFragmentBag toData(); // a Data instance
	public RoleFragmentBag toBean(); // a Bean instance
	public RoleFragmentBag toDataIf(); // a Data instance If need. else return this
	public RoleFragmentBag toBeanIf(); // a Bean instance If need. else return this

	public int getCapacity(); // 包裹容量 
	public java.util.Map<Integer, xbean.Fragment> getItems(); // 背包里的碎片列表
	public java.util.Map<Integer, xbean.Fragment> getItemsAsData(); // 背包里的碎片列表

	public void setCapacity(int _v_); // 包裹容量 
}
