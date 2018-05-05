
package xbean;

public interface AmuletProperty extends xdb.Bean {
	public AmuletProperty copy(); // deep clone
	public AmuletProperty toData(); // a Data instance
	public AmuletProperty toBean(); // a Bean instance
	public AmuletProperty toDataIf(); // a Data instance If need. else return this
	public AmuletProperty toBeanIf(); // a Bean instance If need. else return this

	public int getPropindex(); // 属性id
	public int getIslock(); // 是否锁定，0为未锁定，1为锁定
	public int getSkillid(); // 技能id
	public int getProfessionid(); // 职业id
	public int getAddlevel(); // 技能增值

	public void setPropindex(int _v_); // 属性id
	public void setIslock(int _v_); // 是否锁定，0为未锁定，1为锁定
	public void setSkillid(int _v_); // 技能id
	public void setProfessionid(int _v_); // 职业id
	public void setAddlevel(int _v_); // 技能增值
}
