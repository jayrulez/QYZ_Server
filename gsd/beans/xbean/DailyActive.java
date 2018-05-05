
package xbean;

public interface DailyActive extends xdb.Bean {
	public DailyActive copy(); // deep clone
	public DailyActive toData(); // a Data instance
	public DailyActive toBean(); // a Bean instance
	public DailyActive toDataIf(); // a Data instance If need. else return this
	public DailyActive toBeanIf(); // a Bean instance If need. else return this

	public java.util.Map<Integer, Integer> getActivetimes(); // 各种活动完成的次数
	public java.util.Map<Integer, Integer> getActivetimesAsData(); // 各种活动完成的次数
	public java.util.List<Integer> getReceivedbonus(); // 已领取的奖励类型
	public java.util.List<Integer> getReceivedbonusAsData(); // 已领取的奖励类型
	public int getActivescores(); // 完成活动得到的总积分
	public java.util.Map<Integer, Integer> getUndoactive(); // 每种活动当天未完成的次数
	public java.util.Map<Integer, Integer> getUndoactiveAsData(); // 每种活动当天未完成的次数
	public java.util.Map<Integer, Integer> getEventdonetimes(); // 每种活动当天完成总次数，找回用
	public java.util.Map<Integer, Integer> getEventdonetimesAsData(); // 每种活动当天完成总次数，找回用
	public int getLastactivelvl(); // 昨日最后活跃的等级，找回用

	public void setActivescores(int _v_); // 完成活动得到的总积分
	public void setLastactivelvl(int _v_); // 昨日最后活跃的等级，找回用
}
