
package xbean;

public interface RoleTalismanBag extends xdb.Bean {
	public RoleTalismanBag copy(); // deep clone
	public RoleTalismanBag toData(); // a Data instance
	public RoleTalismanBag toBean(); // a Bean instance
	public RoleTalismanBag toDataIf(); // a Data instance If need. else return this
	public RoleTalismanBag toBeanIf(); // a Bean instance If need. else return this

	public int getCapacity(); // 包裹容量
	public java.util.Map<Integer, xbean.Talisman> getTalismans(); // 所有法宝,key为包裹编号
	public java.util.Map<Integer, xbean.Talisman> getTalismansAsData(); // 所有法宝,key为包裹编号
	public java.util.Map<Integer, xbean.Talisman> getEquipedtalismans(); // 所有法宝,key为包裹编号
	public java.util.Map<Integer, xbean.Talisman> getEquipedtalismansAsData(); // 所有法宝,key为包裹编号
	public int getLuckytype(); // 当前法宝洗练运势，为0时表示没有
	public int getLuckywashtimes(); // 在当前运势下已经洗练的次数，策划会配置一个上限
	public int getMaxcombatpower(); // 达到过的最大战斗力

	public void setCapacity(int _v_); // 包裹容量
	public void setLuckytype(int _v_); // 当前法宝洗练运势，为0时表示没有
	public void setLuckywashtimes(int _v_); // 在当前运势下已经洗练的次数，策划会配置一个上限
	public void setMaxcombatpower(int _v_); // 达到过的最大战斗力
}
