
package xbean;

public interface RoleJadeInfo extends xdb.Bean {
	public RoleJadeInfo copy(); // deep clone
	public RoleJadeInfo toData(); // a Data instance
	public RoleJadeInfo toBean(); // a Bean instance
	public RoleJadeInfo toDataIf(); // a Data instance If need. else return this
	public RoleJadeInfo toBeanIf(); // a Bean instance If need. else return this

	public int getLevel(); // 玉佩等级
	public int getBonus(); // 玉佩增加值
	public int getHolenum(); // 玉佩孔打开的数量
	public java.util.Map<Integer, xbean.Jewelry> getJewelry(); // 装备到玉佩上的宝珠
	public java.util.Map<Integer, xbean.Jewelry> getJewelryAsData(); // 装备到玉佩上的宝珠
	public java.util.List<xbean.Jewelry> getJewelrybag(); // 宝珠背包
	public java.util.List<xbean.Jewelry> getJewelrybagAsData(); // 宝珠背包
	public int getJewelrygetlevel(); // 猎取师档位

	public void setLevel(int _v_); // 玉佩等级
	public void setBonus(int _v_); // 玉佩增加值
	public void setHolenum(int _v_); // 玉佩孔打开的数量
	public void setJewelrygetlevel(int _v_); // 猎取师档位
}
