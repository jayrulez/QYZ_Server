
package xbean;

public interface RoleEquipDepot extends xdb.Bean {
	public RoleEquipDepot copy(); // deep clone
	public RoleEquipDepot toData(); // a Data instance
	public RoleEquipDepot toBean(); // a Bean instance
	public RoleEquipDepot toDataIf(); // a Data instance If need. else return this
	public RoleEquipDepot toBeanIf(); // a Bean instance If need. else return this

	public int getCapacity(); // 包裹容量 
	public java.util.NavigableMap<Integer, xbean.Equip> getEquipbag(); // 装备包裹，key为物品的包裹编号
	public java.util.NavigableMap<Integer, xbean.Equip> getEquipbagAsData(); // 装备包裹，key为物品的包裹编号

	public void setCapacity(int _v_); // 包裹容量 
}
