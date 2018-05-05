
package xbean;

public interface RoleTalismanDepot extends xdb.Bean {
	public RoleTalismanDepot copy(); // deep clone
	public RoleTalismanDepot toData(); // a Data instance
	public RoleTalismanDepot toBean(); // a Bean instance
	public RoleTalismanDepot toDataIf(); // a Data instance If need. else return this
	public RoleTalismanDepot toBeanIf(); // a Bean instance If need. else return this

	public int getCapacity(); // 包裹容量
	public java.util.Map<Integer, xbean.Talisman> getTalismans(); // 所有法宝,key为包裹编号
	public java.util.Map<Integer, xbean.Talisman> getTalismansAsData(); // 所有法宝,key为包裹编号

	public void setCapacity(int _v_); // 包裹容量
}
