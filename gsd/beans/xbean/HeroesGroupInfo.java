
package xbean;

public interface HeroesGroupInfo extends xdb.Bean {
	public HeroesGroupInfo copy(); // deep clone
	public HeroesGroupInfo toData(); // a Data instance
	public HeroesGroupInfo toBean(); // a Bean instance
	public HeroesGroupInfo toDataIf(); // a Data instance If need. else return this
	public HeroesGroupInfo toBeanIf(); // a Bean instance If need. else return this

	public int getRefreshtime(); // 已经进行过的刷新次数
	public int getEctypeid(); // 上次随机到的副本id，为0或空则取默认值

	public void setRefreshtime(int _v_); // 已经进行过的刷新次数
	public void setEctypeid(int _v_); // 上次随机到的副本id，为0或空则取默认值
}
