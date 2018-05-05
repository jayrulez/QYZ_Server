
package xbean;

public interface RoleFamily extends xdb.Bean {
	public RoleFamily copy(); // deep clone
	public RoleFamily toData(); // a Data instance
	public RoleFamily toBean(); // a Bean instance
	public RoleFamily toDataIf(); // a Data instance If need. else return this
	public RoleFamily toBeanIf(); // a Bean instance If need. else return this

	public long getCurrentfid(); // 当前所在的家族id
	public java.util.Map<Long, Long> getRequestedfamily(); // 申请过的家族信息列表
	public java.util.Map<Long, Long> getRequestedfamilyAsData(); // 申请过的家族信息列表
	public long getLastquittime(); // 最近一次退出家族的时间
	public int getTotalquitnum(); // 总共退出家族的次数
	public int getHasjoinpartytoday(); // 今天是否参加过仙府聚宴, 0,否，1,是
	public int getIsfindbackparty(); // 是否找回昨天的仙府聚宴，0，否，1，是

	public void setCurrentfid(long _v_); // 当前所在的家族id
	public void setLastquittime(long _v_); // 最近一次退出家族的时间
	public void setTotalquitnum(int _v_); // 总共退出家族的次数
	public void setHasjoinpartytoday(int _v_); // 今天是否参加过仙府聚宴, 0,否，1,是
	public void setIsfindbackparty(int _v_); // 是否找回昨天的仙府聚宴，0，否，1，是
}
