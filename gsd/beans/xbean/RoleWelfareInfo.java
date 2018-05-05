
package xbean;

public interface RoleWelfareInfo extends xdb.Bean {
	public RoleWelfareInfo copy(); // deep clone
	public RoleWelfareInfo toData(); // a Data instance
	public RoleWelfareInfo toBean(); // a Bean instance
	public RoleWelfareInfo toDataIf(); // a Data instance If need. else return this
	public RoleWelfareInfo toBeanIf(); // a Bean instance If need. else return this

	public long getLastsigntime(); // 上次签到的时间
	public int getTotalsigncount(); // 总的签到天数
	public int getContinuesigncount(); // 连续签到天数,每月开始都重置
	public java.util.List<Integer> getSigndate(); // 当月已签到的日期,每30天清空一次
	public java.util.List<Integer> getSigndateAsData(); // 当月已签到的日期,每30天清空一次
	public int getHastodaysign(); // 记录今天是否已经签到
	public java.util.List<Integer> getReceivedmonthcarddate(); // 已领取每月卡专属礼包的日期,30天清空一次
	public java.util.List<Integer> getReceivedmonthcarddateAsData(); // 已领取每月卡专属礼包的日期,30天清空一次
	public java.util.List<Integer> getReceivedonlinegift(); // 已经领取的每日在线时长奖励，每天0点清空
	public java.util.List<Integer> getReceivedonlinegiftAsData(); // 已经领取的每日在线时长奖励，每天0点清空
	public java.util.List<Integer> getReceivednewgift(); // 已经领取的新手礼包id
	public java.util.List<Integer> getReceivednewgiftAsData(); // 已经领取的新手礼包id
	public java.util.List<Integer> getReceiveconlogingift(); // 已经领取的连续登陆礼包
	public java.util.List<Integer> getReceiveconlogingiftAsData(); // 已经领取的连续登陆礼包
	public int getWishtimes(); // 每天已许愿次数,上限为5次,其中免费许愿一次，vip购买宝碟可额外许愿，每天0点清空
	public long getLastwishtime(); // 上次许愿时间
	public long getLastwishpetid(); // 上次许愿的伙伴
	public java.util.List<Integer> getReceivedgrowplangift(); // 已领取的成长计划礼包
	public java.util.List<Integer> getReceivedgrowplangiftAsData(); // 已领取的成长计划礼包
	public java.util.List<Integer> getReceivedpaycharge(); // 已领取的充值和消费礼包
	public java.util.List<Integer> getReceivedpaychargeAsData(); // 已领取的充值和消费礼包
	public int getIseatlunch(); // 是否领取午餐；0，否；1，是
	public int getIseatdinner(); // 是否领取晚餐
	public java.util.List<Integer> getThreegrowplan(); // 领取的成长计划礼包，第一档
	public java.util.List<Integer> getThreegrowplanAsData(); // 领取的成长计划礼包，第一档
	public java.util.List<Integer> getFivegrowplan(); // 
	public java.util.List<Integer> getFivegrowplanAsData(); // 
	public java.util.List<Integer> getSevengrowplan(); // 
	public java.util.List<Integer> getSevengrowplanAsData(); // 

	public void setLastsigntime(long _v_); // 上次签到的时间
	public void setTotalsigncount(int _v_); // 总的签到天数
	public void setContinuesigncount(int _v_); // 连续签到天数,每月开始都重置
	public void setHastodaysign(int _v_); // 记录今天是否已经签到
	public void setWishtimes(int _v_); // 每天已许愿次数,上限为5次,其中免费许愿一次，vip购买宝碟可额外许愿，每天0点清空
	public void setLastwishtime(long _v_); // 上次许愿时间
	public void setLastwishpetid(long _v_); // 上次许愿的伙伴
	public void setIseatlunch(int _v_); // 是否领取午餐；0，否；1，是
	public void setIseatdinner(int _v_); // 是否领取晚餐
}
