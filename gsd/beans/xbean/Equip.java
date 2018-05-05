
package xbean;

public interface Equip extends xdb.Bean {
	public Equip copy(); // deep clone
	public Equip toData(); // a Data instance
	public Equip toBean(); // a Bean instance
	public Equip toDataIf(); // a Data instance If need. else return this
	public Equip toBeanIf(); // a Bean instance If need. else return this

	public long getEquipid(); // 装备id
	public int getModelid(); // model id
	public int getPosition(); // 包裹中的位置
	public long getExpiretime(); // 过期时间
	public boolean getIsbind(); // 是否绑定
	public xbean.NormalEquip getNormalequip(); // 普通装备的属性
	public xbean.Accessory getAccessory(); // 饰品类装备的属性

	public void setEquipid(long _v_); // 装备id
	public void setModelid(int _v_); // model id
	public void setPosition(int _v_); // 包裹中的位置
	public void setExpiretime(long _v_); // 过期时间
	public void setIsbind(boolean _v_); // 是否绑定
}
