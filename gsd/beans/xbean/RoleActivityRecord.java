
package xbean;

public interface RoleActivityRecord extends xdb.Bean {
	public RoleActivityRecord copy(); // deep clone
	public RoleActivityRecord toData(); // a Data instance
	public RoleActivityRecord toBean(); // a Bean instance
	public RoleActivityRecord toDataIf(); // a Data instance If need. else return this
	public RoleActivityRecord toBeanIf(); // a Bean instance If need. else return this

	public long getTotalcostyuanbbao(); // 累计花费元宝奖励
	public int getKillworldboss(); // 杀死世界boss个数
	public int getArenawin(); // 竞技场胜场
	public int getTeamspeedwin(); // 鸿蒙争霸胜场
	public int getTeamfightwin(); // 天下会武胜场

	public void setTotalcostyuanbbao(long _v_); // 累计花费元宝奖励
	public void setKillworldboss(int _v_); // 杀死世界boss个数
	public void setArenawin(int _v_); // 竞技场胜场
	public void setTeamspeedwin(int _v_); // 鸿蒙争霸胜场
	public void setTeamfightwin(int _v_); // 天下会武胜场
}
