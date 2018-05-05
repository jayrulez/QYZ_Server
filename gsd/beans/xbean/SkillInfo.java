
package xbean;

public interface SkillInfo extends xdb.Bean {
	public SkillInfo copy(); // deep clone
	public SkillInfo toData(); // a Data instance
	public SkillInfo toBean(); // a Bean instance
	public SkillInfo toDataIf(); // a Data instance If need. else return this
	public SkillInfo toBeanIf(); // a Bean instance If need. else return this

	public int getLevel(); // 

	public void setLevel(int _v_); // 
}
