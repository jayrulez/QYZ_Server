
package xbean;

public interface RoleItemBag extends xdb.Bean {
	public RoleItemBag copy(); // deep clone
	public RoleItemBag toData(); // a Data instance
	public RoleItemBag toBean(); // a Bean instance
	public RoleItemBag toDataIf(); // a Data instance If need. else return this
	public RoleItemBag toBeanIf(); // a Bean instance If need. else return this

	public int getCapacity(); // 包裹容量 
	public java.util.Map<Integer, xbean.Item> getItems(); // 背包里的物品列表，key为格子index
	public java.util.Map<Integer, xbean.Item> getItemsAsData(); // 背包里的物品列表，key为格子index

	public void setCapacity(int _v_); // 包裹容量 
}
