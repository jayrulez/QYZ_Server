
package xbean;

public interface RolePet extends xdb.Bean {
	public RolePet copy(); // deep clone
	public RolePet toData(); // a Data instance
	public RolePet toBean(); // a Bean instance
	public RolePet toDataIf(); // a Data instance If need. else return this
	public RolePet toBeanIf(); // a Bean instance If need. else return this

	public java.util.Map<Integer, xbean.Pet> getPetmap(); // 
	public java.util.Map<Integer, xbean.Pet> getPetmapAsData(); // 
	public java.util.List<Integer> getFightpets(); // 
	public java.util.List<Integer> getFightpetsAsData(); // 
	public int getActivepetmodelid(); // 
	public java.util.Map<Integer, Integer> getPetfragmentmap(); // 
	public java.util.Map<Integer, Integer> getPetfragmentmapAsData(); // 
	public int getTotalcombatpower(); // 

	public void setActivepetmodelid(int _v_); // 
	public void setTotalcombatpower(int _v_); // 
}
