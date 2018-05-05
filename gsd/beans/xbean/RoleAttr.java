
package xbean;

public interface RoleAttr extends xdb.Bean {
	public RoleAttr copy(); // deep clone
	public RoleAttr toData(); // a Data instance
	public RoleAttr toBean(); // a Bean instance
	public RoleAttr toDataIf(); // a Data instance If need. else return this
	public RoleAttr toBeanIf(); // a Bean instance If need. else return this

	public long getRoleid(); // 
	public int getRolecombatpower(); // 
	public java.util.List<Float> getRawattrs(); // 
	public java.util.List<Float> getRawattrsAsData(); // 
	public java.util.List<Float> getFinalattrs(); // 
	public java.util.List<Float> getFinalattrsAsData(); // 
	public java.util.Map<String, xbean.GroupAttr> getGroupattrs(); // 
	public java.util.Map<String, xbean.GroupAttr> getGroupattrsAsData(); // 
	public int getHp(); // 
	public int getMp(); // 
	public java.util.Map<Integer, Long> getSkillcolddowns(); // 
	public java.util.Map<Integer, Long> getSkillcolddownsAsData(); // 
	public int getResethpmp(); // 
	public int getPetcombatpower(); // 
	public int getTotalcombatpower(); // 

	public void setRoleid(long _v_); // 
	public void setRolecombatpower(int _v_); // 
	public void setHp(int _v_); // 
	public void setMp(int _v_); // 
	public void setResethpmp(int _v_); // 
	public void setPetcombatpower(int _v_); // 
	public void setTotalcombatpower(int _v_); // 
}
