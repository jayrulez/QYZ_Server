
package xbean;

public interface FamilyMember extends xdb.Bean {
	public FamilyMember copy(); // deep clone
	public FamilyMember toData(); // a Data instance
	public FamilyMember toBean(); // a Bean instance
	public FamilyMember toDataIf(); // a Data instance If need. else return this
	public FamilyMember toBeanIf(); // a Bean instance If need. else return this

	public long getJointime(); // 加入家族的时间，如果familyid为0，该时间为退出时间，如果为0表明未加入也未退出过
	public int getFamilyjob(); // 家族的职位，组长，青龙使，副组长等等
	public long getRoleid(); // 个人角色Id,为了方便排序找到最大贡献度
	public long getPersonalbuild(); // 个人贡献度,转移族长的时候用
	public long getDailybuild(); // 每日贡献度,每天清空一次，用下面的时间判断

	public void setJointime(long _v_); // 加入家族的时间，如果familyid为0，该时间为退出时间，如果为0表明未加入也未退出过
	public void setFamilyjob(int _v_); // 家族的职位，组长，青龙使，副组长等等
	public void setRoleid(long _v_); // 个人角色Id,为了方便排序找到最大贡献度
	public void setPersonalbuild(long _v_); // 个人贡献度,转移族长的时候用
	public void setDailybuild(long _v_); // 每日贡献度,每天清空一次，用下面的时间判断
}
