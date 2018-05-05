
package xbean;

public interface HuiWuBattle extends xdb.Bean {
	public HuiWuBattle copy(); // deep clone
	public HuiWuBattle toData(); // a Data instance
	public HuiWuBattle toBean(); // a Bean instance
	public HuiWuBattle toDataIf(); // a Data instance If need. else return this
	public HuiWuBattle toBeanIf(); // a Bean instance If need. else return this

	public int getIndex(); // 
	public long getRoleid1(); // 
	public long getRoleid2(); // 
	public int getState(); // cfg.huiwu.BattleState
	public long getMapid(); // 

	public void setIndex(int _v_); // 
	public void setRoleid1(long _v_); // 
	public void setRoleid2(long _v_); // 
	public void setState(int _v_); // cfg.huiwu.BattleState
	public void setMapid(long _v_); // 
}
