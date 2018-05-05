
package xbean;

public interface Family extends xdb.Bean {
	public Family copy(); // deep clone
	public Family toData(); // a Data instance
	public Family toBean(); // a Bean instance
	public Family toDataIf(); // a Data instance If need. else return this
	public Family toBeanIf(); // a Bean instance If need. else return this

	public long getFamilyid(); // 家族id
	public String getFamilyname(); // 家族名字
	public com.goldhuman.Common.Octets getFamilynameOctets(); // 家族名字
	public int getFlevel(); // 家族等级
	public long getMoney(); // 家族资金
	public int getCurlvlbuilddegree(); // 当前等级家族建设度
	public int getTotalbuilddegree(); // 总的家族建设度,排行榜使用
	public long getTotalbanggong(); // 总的家族帮贡
	public String getDeclaration(); // 家族宣言
	public com.goldhuman.Common.Octets getDeclarationOctets(); // 家族宣言
	public String getPublicinfo(); // 家族公告
	public com.goldhuman.Common.Octets getPublicinfoOctets(); // 家族公告
	public long getPublictime(); // 公告时间
	public int getMalllevel(); // 家族商店级别
	public java.util.List<xbean.FamilyLogReport> getLogs(); // 家族日志信息
	public java.util.List<xbean.FamilyLogReport> getLogsAsData(); // 家族日志信息
	public java.util.Map<Long, Long> getRequestinglist(); // 申请成员列表,key为角色id，value为申请时间
	public java.util.Map<Long, Long> getRequestinglistAsData(); // 申请成员列表,key为角色id，value为申请时间
	public xbean.FamilyActivity getActivity(); // 家族神兽信息
	public xbean.FamilyGodAnimalAct getBeatanimalactivity(); // 神兽挑战信息
	public xbean.FamilyWelfare getWelfare(); // 家族福利相关的信息
	public long getChiefid(); // 组长角色id
	public java.util.Map<Integer, xbean.FamilyJobStaffList> getJobinfo(); // 家族职位信息，key为职位id，value为该职位人员信息允许一个职位多个人
	public java.util.Map<Integer, xbean.FamilyJobStaffList> getJobinfoAsData(); // 家族职位信息，key为职位id，value为该职位人员信息允许一个职位多个人
	public java.util.Map<Long, xbean.FamilyMember> getMembers(); // 家族成员信息
	public java.util.Map<Long, xbean.FamilyMember> getMembersAsData(); // 家族成员信息
	public long getUpdatetime(); // 信息更新时间
	public long getCreatetime(); // 家族创建的时间
	public long getLastpartyopentime(); // 家族聚会上次开启时间
	public long getLastpartycalltime(); // 上次召集教众时间
	public int getIssystemfam(); // 是否是系统创建的家族，0否，1是
	public java.util.Map<Long, Long> getInvitelist(); // 邀请列表
	public java.util.Map<Long, Long> getInvitelistAsData(); // 邀请列表
	public long getLastresettime(); // 

	public void setFamilyid(long _v_); // 家族id
	public void setFamilyname(String _v_); // 家族名字
	public void setFamilynameOctets(com.goldhuman.Common.Octets _v_); // 家族名字
	public void setFlevel(int _v_); // 家族等级
	public void setMoney(long _v_); // 家族资金
	public void setCurlvlbuilddegree(int _v_); // 当前等级家族建设度
	public void setTotalbuilddegree(int _v_); // 总的家族建设度,排行榜使用
	public void setTotalbanggong(long _v_); // 总的家族帮贡
	public void setDeclaration(String _v_); // 家族宣言
	public void setDeclarationOctets(com.goldhuman.Common.Octets _v_); // 家族宣言
	public void setPublicinfo(String _v_); // 家族公告
	public void setPublicinfoOctets(com.goldhuman.Common.Octets _v_); // 家族公告
	public void setPublictime(long _v_); // 公告时间
	public void setMalllevel(int _v_); // 家族商店级别
	public void setChiefid(long _v_); // 组长角色id
	public void setUpdatetime(long _v_); // 信息更新时间
	public void setCreatetime(long _v_); // 家族创建的时间
	public void setLastpartyopentime(long _v_); // 家族聚会上次开启时间
	public void setLastpartycalltime(long _v_); // 上次召集教众时间
	public void setIssystemfam(int _v_); // 是否是系统创建的家族，0否，1是
	public void setLastresettime(long _v_); // 
}
