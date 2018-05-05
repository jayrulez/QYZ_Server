
package xbean;

public interface Bonus extends xdb.Bean {
	public Bonus copy(); // deep clone
	public Bonus toData(); // a Data instance
	public Bonus toBean(); // a Bean instance
	public Bonus toDataIf(); // a Data instance If need. else return this
	public Bonus toBeanIf(); // a Bean instance If need. else return this

	public int getBindtype(); // 
	public java.util.Map<Integer, Integer> getCurrencys(); // 货币(exp,vipexp也算货币)
	public java.util.Map<Integer, Integer> getCurrencysAsData(); // 货币(exp,vipexp也算货币)
	public java.util.Map<Integer, Integer> getItems(); // 物品，包含装备,碎片和消耗性物品
	public java.util.Map<Integer, Integer> getItemsAsData(); // 物品，包含装备,碎片和消耗性物品
	public java.util.List<xbean.Equip> getEquips(); // 
	public java.util.List<xbean.Equip> getEquipsAsData(); // 

	public void setBindtype(int _v_); // 
}
