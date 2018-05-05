
package xbean;

public interface RoleSkill extends xdb.Bean {
	public RoleSkill copy(); // deep clone
	public RoleSkill toData(); // a Data instance
	public RoleSkill toBean(); // a Bean instance
	public RoleSkill toDataIf(); // a Data instance If need. else return this
	public RoleSkill toBeanIf(); // a Bean instance If need. else return this

	public java.util.Map<Integer, xbean.SkillInfo> getSkills(); // 
	public java.util.Map<Integer, xbean.SkillInfo> getSkillsAsData(); // 
	public java.util.Map<Integer, Integer> getEquipskillpositions(); // 
	public java.util.Map<Integer, Integer> getEquipskillpositionsAsData(); // 

}
