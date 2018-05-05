
package xbean;

public interface TeamMember extends xdb.Bean {
	public TeamMember copy(); // deep clone
	public TeamMember toData(); // a Data instance
	public TeamMember toBean(); // a Bean instance
	public TeamMember toDataIf(); // a Data instance If need. else return this
	public TeamMember toBeanIf(); // a Bean instance If need. else return this

	public long getRoleid(); // 
	public long getJointime(); // 时间，根据不同的集合表示不同的时间，申请时间，发送邀请的时间，加入队伍的时间等等
	public int getFollow(); // 是否在跟随状态,0为非跟随，1为跟随
	public long getFollowtime(); // 跟随开始的时间

	public void setRoleid(long _v_); // 
	public void setJointime(long _v_); // 时间，根据不同的集合表示不同的时间，申请时间，发送邀请的时间，加入队伍的时间等等
	public void setFollow(int _v_); // 是否在跟随状态,0为非跟随，1为跟随
	public void setFollowtime(long _v_); // 跟随开始的时间
}
