
package xbean;

public interface HeroesGroupMemInfo extends xdb.Bean {
	public HeroesGroupMemInfo copy(); // deep clone
	public HeroesGroupMemInfo toData(); // a Data instance
	public HeroesGroupMemInfo toBean(); // a Bean instance
	public HeroesGroupMemInfo toDataIf(); // a Data instance If need. else return this
	public HeroesGroupMemInfo toBeanIf(); // a Bean instance If need. else return this

	public int getEctypeid(); // 
	public int getRandomtime(); // 已经随机的次数
	public java.util.List<xbean.RandomBonus> getBonus(); // 上次随机但未领取的奖励，领取后则为空
	public java.util.List<xbean.RandomBonus> getBonusAsData(); // 上次随机但未领取的奖励，领取后则为空

	public void setEctypeid(int _v_); // 
	public void setRandomtime(int _v_); // 已经随机的次数
}
