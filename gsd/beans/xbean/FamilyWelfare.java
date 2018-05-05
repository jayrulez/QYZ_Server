
package xbean;

public interface FamilyWelfare extends xdb.Bean {
	public FamilyWelfare copy(); // deep clone
	public FamilyWelfare toData(); // a Data instance
	public FamilyWelfare toBean(); // a Bean instance
	public FamilyWelfare toDataIf(); // a Data instance If need. else return this
	public FamilyWelfare toBeanIf(); // a Bean instance If need. else return this

	public java.util.Map<Integer, xbean.FamilySkill> getSkills(); // 家族技能信息
	public java.util.Map<Integer, xbean.FamilySkill> getSkillsAsData(); // 家族技能信息
	public int getMaxskilllevel(); // 家族技能最大值

	public void setMaxskilllevel(int _v_); // 家族技能最大值
}
