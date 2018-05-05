
package xbean;

public interface RoleMap extends xdb.Bean {
	public RoleMap copy(); // deep clone
	public RoleMap toData(); // a Data instance
	public RoleMap toBean(); // a Bean instance
	public RoleMap toDataIf(); // a Data instance If need. else return this
	public RoleMap toBeanIf(); // a Bean instance If need. else return this

	public java.util.Map<Integer, Integer> getPkstates(); // 在各大地图中的pk状态 worldid -> cfg.fight.PKState
	public java.util.Map<Integer, Integer> getPkstatesAsData(); // 在各大地图中的pk状态 worldid -> cfg.fight.PKState
	public boolean getFinishprologue(); // 
	public int getHp(); // 
	public int getMp(); // 
	public java.util.List<xbean.RoleMapInfo> getPrevs(); // 
	public java.util.List<xbean.RoleMapInfo> getPrevsAsData(); // 
	public boolean getIsnew(); // 

	public void setFinishprologue(boolean _v_); // 
	public void setHp(int _v_); // 
	public void setMp(int _v_); // 
	public void setIsnew(boolean _v_); // 
}
