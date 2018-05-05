
package xbean;

public interface RolePay extends xdb.Bean {
	public RolePay copy(); // deep clone
	public RolePay toData(); // a Data instance
	public RolePay toBean(); // a Bean instance
	public RolePay toDataIf(); // a Data instance If need. else return this
	public RolePay toBeanIf(); // a Bean instance If need. else return this

	public boolean getCangetfirstbonus(); // 是否首冲过
	public boolean getIsfirstpayused(); // 是否领取过首冲奖励
	public java.util.Map<Integer, Boolean> getIsentryfirstpay(); // 每个充值条目是否首冲过
	public java.util.Map<Integer, Boolean> getIsentryfirstpayAsData(); // 每个充值条目是否首冲过
	public long getTotalpay(); // 累计充值，以分为单位，充值时花费人民币，得到元宝和绑定元宝
	public long getLastrefreshtime(); // 上次刷新时间
	public int getDailypaystatus(); // 每日充值状态，分为未充值，充值没领奖，充值已领奖
	public long getDailytotalpay(); // 每日累计充值
	public int getDailytotalpaystatus(); // 每日累计充值状态
	public long getRoleallpay(); // 角色所有充钱的记录，包含月卡和成长计划
	public int getDailyactivepay(); // 每日特价购是否使用过
	public boolean getHasgotpayreturn(); // 已经得到充值返还
	public long getTotalyunbao(); // 
	public long getTotalbindyuanbao(); // 
	public long getTotalvipexp(); // 

	public void setCangetfirstbonus(boolean _v_); // 是否首冲过
	public void setIsfirstpayused(boolean _v_); // 是否领取过首冲奖励
	public void setTotalpay(long _v_); // 累计充值，以分为单位，充值时花费人民币，得到元宝和绑定元宝
	public void setLastrefreshtime(long _v_); // 上次刷新时间
	public void setDailypaystatus(int _v_); // 每日充值状态，分为未充值，充值没领奖，充值已领奖
	public void setDailytotalpay(long _v_); // 每日累计充值
	public void setDailytotalpaystatus(int _v_); // 每日累计充值状态
	public void setRoleallpay(long _v_); // 角色所有充钱的记录，包含月卡和成长计划
	public void setDailyactivepay(int _v_); // 每日特价购是否使用过
	public void setHasgotpayreturn(boolean _v_); // 已经得到充值返还
	public void setTotalyunbao(long _v_); // 
	public void setTotalbindyuanbao(long _v_); // 
	public void setTotalvipexp(long _v_); // 
}
