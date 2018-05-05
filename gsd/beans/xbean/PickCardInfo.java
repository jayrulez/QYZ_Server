
package xbean;

public interface PickCardInfo extends xdb.Bean {
	public PickCardInfo copy(); // deep clone
	public PickCardInfo toData(); // a Data instance
	public PickCardInfo toBean(); // a Bean instance
	public PickCardInfo toDataIf(); // a Data instance If need. else return this
	public PickCardInfo toBeanIf(); // a Bean instance If need. else return this

	public long getLastfreehuoban(); // 上次伙伴虚拟币免费单抽时间
	public int getHuobanhighyuanbao(); // 伙伴高额元宝抽卡次数
	public int getHuobanlowyuanbao(); // 伙伴低额元宝抽卡次数
	public int getFabaoyuanbao(); // 法宝元宝抽卡次数
	public int getFabaofree(); // 法宝元宝免费次数
	public int getFabaoxunibi(); // 法宝虚拟币抽卡次数，免费次数也统计在内

	public void setLastfreehuoban(long _v_); // 上次伙伴虚拟币免费单抽时间
	public void setHuobanhighyuanbao(int _v_); // 伙伴高额元宝抽卡次数
	public void setHuobanlowyuanbao(int _v_); // 伙伴低额元宝抽卡次数
	public void setFabaoyuanbao(int _v_); // 法宝元宝抽卡次数
	public void setFabaofree(int _v_); // 法宝元宝免费次数
	public void setFabaoxunibi(int _v_); // 法宝虚拟币抽卡次数，免费次数也统计在内
}
