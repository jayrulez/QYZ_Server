
package xbean;

public interface RoleEctype extends xdb.Bean {
	public RoleEctype copy(); // deep clone
	public RoleEctype toData(); // a Data instance
	public RoleEctype toBean(); // a Bean instance
	public RoleEctype toDataIf(); // a Data instance If need. else return this
	public RoleEctype toBeanIf(); // a Bean instance If need. else return this

	public java.util.Map<Integer, xbean.ClimbTowerInfo> getClimbtowers(); // 
	public java.util.Map<Integer, xbean.ClimbTowerInfo> getClimbtowersAsData(); // 
	public java.util.Map<Integer, xbean.ChapterInfo> getChapters(); // 
	public java.util.Map<Integer, xbean.ChapterInfo> getChaptersAsData(); // 
	public java.util.Map<Integer, xbean.DailyInfo> getDailys(); // key->ectypetype
	public java.util.Map<Integer, xbean.DailyInfo> getDailysAsData(); // key->ectypetype
	public java.util.Map<Integer, Integer> getMultistory(); // 多人剧情副本挑战的得星记录
	public java.util.Map<Integer, Integer> getMultistoryAsData(); // 多人剧情副本挑战的得星记录
	public java.util.Map<Integer, xbean.HeroesGroupInfo> getHerogroups(); // groupid->HeroesGroupInfo
	public java.util.Map<Integer, xbean.HeroesGroupInfo> getHerogroupsAsData(); // groupid->HeroesGroupInfo
	public xbean.TeamFightInfo getTeamfight(); // 
	public int getMatchtype(); // cfg.ectype.MatchType
	public long getNextmatchtime(); // 
	public int getMultiectypid(); // 如果是在匹配多人剧情，需要记一下

	public void setMatchtype(int _v_); // cfg.ectype.MatchType
	public void setNextmatchtime(long _v_); // 
	public void setMultiectypid(int _v_); // 如果是在匹配多人剧情，需要记一下
}
