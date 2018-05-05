
package xbean;

public interface RoleItemDepot extends xdb.Bean {
	public RoleItemDepot copy(); // deep clone
	public RoleItemDepot toData(); // a Data instance
	public RoleItemDepot toBean(); // a Bean instance
	public RoleItemDepot toDataIf(); // a Data instance If need. else return this
	public RoleItemDepot toBeanIf(); // a Bean instance If need. else return this

	public int getCapacity(); // 包裹容量 
	public java.util.Map<Integer, xbean.Item> getItems(); // 背包里的物品列表，key为包裹编号
	public java.util.Map<Integer, xbean.Item> getItemsAsData(); // 背包里的物品列表，key为包裹编号

	public void setCapacity(int _v_); // 包裹容量 
}
