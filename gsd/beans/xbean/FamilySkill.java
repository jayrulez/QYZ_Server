
package xbean;

public interface FamilySkill extends xdb.Bean {
	public FamilySkill copy(); // deep clone
	public FamilySkill toData(); // a Data instance
	public FamilySkill toBean(); // a Bean instance
	public FamilySkill toDataIf(); // a Data instance If need. else return this
	public FamilySkill toBeanIf(); // a Bean instance If need. else return this

	public int getSkillid(); // 家族技能id
	public long getStudytime(); // 学习的时间
	public int getLevel(); // 技能的等级
	public long getUptime(); // 技能升级的时间

	public void setSkillid(int _v_); // 家族技能id
	public void setStudytime(long _v_); // 学习的时间
	public void setLevel(int _v_); // 技能的等级
	public void setUptime(long _v_); // 技能升级的时间
}
