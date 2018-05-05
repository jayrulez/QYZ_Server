
package xbean;

public interface RoleTeamInfo extends xdb.Bean {
	public RoleTeamInfo copy(); // deep clone
	public RoleTeamInfo toData(); // a Data instance
	public RoleTeamInfo toBean(); // a Bean instance
	public RoleTeamInfo toDataIf(); // a Data instance If need. else return this
	public RoleTeamInfo toBeanIf(); // a Bean instance If need. else return this

	public long getTeamid(); // 
	public int getAutoacceptrequest(); // 是否自动接收别人的入队申请
	public int getAutoacceptinvite(); // 是否自动接收别人的入队邀请
	public java.util.Map<Long, xbean.TeamMember> getRequesttojoin(); // 请求加入别人队伍的申请，key为队伍的队长角色id,long为发送申请的时间
	public java.util.Map<Long, xbean.TeamMember> getRequesttojoinAsData(); // 请求加入别人队伍的申请，key为队伍的队长角色id,long为发送申请的时间

	public void setTeamid(long _v_); // 
	public void setAutoacceptrequest(int _v_); // 是否自动接收别人的入队申请
	public void setAutoacceptinvite(int _v_); // 是否自动接收别人的入队邀请
}
