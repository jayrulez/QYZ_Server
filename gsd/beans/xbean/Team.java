
package xbean;

public interface Team extends xdb.Bean {
	public Team copy(); // deep clone
	public Team toData(); // a Data instance
	public Team toBean(); // a Bean instance
	public Team toDataIf(); // a Data instance If need. else return this
	public Team toBeanIf(); // a Bean instance If need. else return this

	public long getTeamid(); // teamid
	public long getLeaderid(); // 
	public long getCreatetime(); // 组队时间
	public java.util.Map<Long, xbean.TeamMember> getMembers(); // 队友信息，key为角色id，value为角色加入队伍的时间
	public java.util.Map<Long, xbean.TeamMember> getMembersAsData(); // 队友信息，key为角色id，value为角色加入队伍的时间
	public java.util.Map<Long, Long> getRequestforjoin(); // 请求入队的申请，key为申请加入的角色id
	public java.util.Map<Long, Long> getRequestforjoinAsData(); // 请求入队的申请，key为申请加入的角色id
	public java.util.Map<Long, Long> getInvitetojoin(); // 邀请别人加入自己的队伍，key为邀请人的角色id，value为邀请的时间
	public java.util.Map<Long, Long> getInvitetojoinAsData(); // 邀请别人加入自己的队伍，key为邀请人的角色id，value为邀请的时间
	public java.util.Map<Long, Long> getInvitefollow(); // 邀请别人跟随，key为邀请角色id，value为邀请的时间，邀请有时间冷却限定
	public java.util.Map<Long, Long> getInvitefollowAsData(); // 邀请别人跟随，key为邀请角色id，value为邀请的时间，邀请有时间冷却限定
	public long getLeadertransroleid(); // 队长转移申请发送给的roleid，0为未发出转移申请

	public void setTeamid(long _v_); // teamid
	public void setLeaderid(long _v_); // 
	public void setCreatetime(long _v_); // 组队时间
	public void setLeadertransroleid(long _v_); // 队长转移申请发送给的roleid，0为未发出转移申请
}
