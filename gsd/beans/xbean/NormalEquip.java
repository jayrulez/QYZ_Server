
package xbean;

public interface NormalEquip extends xdb.Bean {
	public NormalEquip copy(); // deep clone
	public NormalEquip toData(); // a Data instance
	public NormalEquip toBean(); // a Bean instance
	public NormalEquip toDataIf(); // a Data instance If need. else return this
	public NormalEquip toBeanIf(); // a Bean instance If need. else return this

	public int getAnneallevel(); // 装备强化等级
	public int getPerfuselevel(); // 装备灌注等级
	public int getGoldcost(); // 追加消耗的虚拟币
	public int getAnnealitemcost(); // 炼器消耗的物品数量
	public int getPerfuseitemcost(); // 灌注消耗的物品数量

	public void setAnneallevel(int _v_); // 装备强化等级
	public void setPerfuselevel(int _v_); // 装备灌注等级
	public void setGoldcost(int _v_); // 追加消耗的虚拟币
	public void setAnnealitemcost(int _v_); // 炼器消耗的物品数量
	public void setPerfuseitemcost(int _v_); // 灌注消耗的物品数量
}
