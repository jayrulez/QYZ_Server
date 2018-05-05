
package xbean;

public interface RoleInfo extends xdb.Bean {
	public RoleInfo copy(); // deep clone
	public RoleInfo toData(); // a Data instance
	public RoleInfo toBean(); // a Bean instance
	public RoleInfo toDataIf(); // a Data instance If need. else return this
	public RoleInfo toBeanIf(); // a Bean instance If need. else return this

	public long getUserid(); // 
	public int getProfession(); // 
	public long getCreatetime(); // 
	public int getServerid(); // 
	public long getLastlogintime(); // 
	public long getTotalonlinetime(); // 
	public long getLastlvluptotalonlinetime(); // 
	public long getLastlogouttime(); // 用来判断是否0点在线的问题
	public int getLogindaycount(); // 总的登录天数
	public long getLastaddlogindaytime(); // 
	public int getContinuesloginday(); // 连续登录天数
	public long getTotaldayonlinetime(); // 每天在线时长，每天登录的时候重置
	public int getLeftgifttimes(); // 剩余连续登录奖励领取次数,每天登录的时候重置
	public int getMonthcardlefttimes(); // 月卡剩余天数
	public int getBuymonthcardtimes(); // 购买月卡次数
	public int getBuygrowplan(); // 是否购买过成长计划
	public int getGrowonetime(); // 购买成长计划1的时间，用登录第几天表示
	public int getBuygrowplantwo(); // 购买成长计划2，苹果测试用
	public int getGrowtwotime(); // 购买成长计划2的时间，用登录第几天表示
	public int getBuygrowplanthree(); // 购买成长计划3，苹果测试用
	public int getGrowthreetime(); // 购买成长计划3的时间，用登录第几天表示
	public String getName(); // 
	public com.goldhuman.Common.Octets getNameOctets(); // 
	public int getChangenametime(); // 
	public int getLevel(); // 
	public long getLastlvluptime(); // 
	public int getViplevel(); // 
	public long getVipexp(); // 
	public java.util.Map<Integer, Long> getCurrencys(); // cfg.currency.CurrencyType -> num
	public java.util.Map<Integer, Long> getCurrencysAsData(); // cfg.currency.CurrencyType -> num
	public int getGender(); // cfg.role.GenderType
	public int getTotalcostyuanbao(); // 累计花费元宝,不算绑定的
	public long getLastaddtilitime(); // 上次自动加体力的时间
	public long getLastworldtalktime(); // 上次世界频道发言时间
	public long getSilentendtime(); // 禁言到期时间
	public int getLeftreporttime(); // 剩余举报次数，每天重置
	public int getBereportedtime(); // 被举报次数，每次被禁言后就清空一次

	public void setUserid(long _v_); // 
	public void setProfession(int _v_); // 
	public void setCreatetime(long _v_); // 
	public void setServerid(int _v_); // 
	public void setLastlogintime(long _v_); // 
	public void setTotalonlinetime(long _v_); // 
	public void setLastlvluptotalonlinetime(long _v_); // 
	public void setLastlogouttime(long _v_); // 用来判断是否0点在线的问题
	public void setLogindaycount(int _v_); // 总的登录天数
	public void setLastaddlogindaytime(long _v_); // 
	public void setContinuesloginday(int _v_); // 连续登录天数
	public void setTotaldayonlinetime(long _v_); // 每天在线时长，每天登录的时候重置
	public void setLeftgifttimes(int _v_); // 剩余连续登录奖励领取次数,每天登录的时候重置
	public void setMonthcardlefttimes(int _v_); // 月卡剩余天数
	public void setBuymonthcardtimes(int _v_); // 购买月卡次数
	public void setBuygrowplan(int _v_); // 是否购买过成长计划
	public void setGrowonetime(int _v_); // 购买成长计划1的时间，用登录第几天表示
	public void setBuygrowplantwo(int _v_); // 购买成长计划2，苹果测试用
	public void setGrowtwotime(int _v_); // 购买成长计划2的时间，用登录第几天表示
	public void setBuygrowplanthree(int _v_); // 购买成长计划3，苹果测试用
	public void setGrowthreetime(int _v_); // 购买成长计划3的时间，用登录第几天表示
	public void setName(String _v_); // 
	public void setNameOctets(com.goldhuman.Common.Octets _v_); // 
	public void setChangenametime(int _v_); // 
	public void setLevel(int _v_); // 
	public void setLastlvluptime(long _v_); // 
	public void setViplevel(int _v_); // 
	public void setVipexp(long _v_); // 
	public void setGender(int _v_); // cfg.role.GenderType
	public void setTotalcostyuanbao(int _v_); // 累计花费元宝,不算绑定的
	public void setLastaddtilitime(long _v_); // 上次自动加体力的时间
	public void setLastworldtalktime(long _v_); // 上次世界频道发言时间
	public void setSilentendtime(long _v_); // 禁言到期时间
	public void setLeftreporttime(int _v_); // 剩余举报次数，每天重置
	public void setBereportedtime(int _v_); // 被举报次数，每次被禁言后就清空一次
}
