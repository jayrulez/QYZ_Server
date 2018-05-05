
package xbean;

public interface RoleEquipBag extends xdb.Bean {
	public RoleEquipBag copy(); // deep clone
	public RoleEquipBag toData(); // a Data instance
	public RoleEquipBag toBean(); // a Bean instance
	public RoleEquipBag toDataIf(); // a Data instance If need. else return this
	public RoleEquipBag toBeanIf(); // a Bean instance If need. else return this

	public int getCapacity(); // 包裹容量 
	public java.util.Map<Integer, xbean.Equip> getEquipmap(); // 装备包裹，key为格子index
	public java.util.Map<Integer, xbean.Equip> getEquipmapAsData(); // 装备包裹，key为格子index
	public java.util.Map<Integer, xbean.Equip> getEquiponbodymap(); // 装备包裹，key为格子index
	public java.util.Map<Integer, xbean.Equip> getEquiponbodymapAsData(); // 装备包裹，key为格子index

	public void setCapacity(int _v_); // 包裹容量 
}
