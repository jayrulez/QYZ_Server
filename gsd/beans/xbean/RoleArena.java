
package xbean;

public interface RoleArena extends xdb.Bean {
	public RoleArena copy(); // deep clone
	public RoleArena toData(); // a Data instance
	public RoleArena toBean(); // a Bean instance
	public RoleArena toDataIf(); // a Data instance If need. else return this
	public RoleArena toBeanIf(); // a Bean instance If need. else return this

	public boolean getOpen(); // 
	public boolean getIsrobot(); // 
	public long getLastupdateshengwangtime(); // 
	public java.util.List<Integer> getChallengeranks(); // 
	public java.util.List<Integer> getChallengeranksAsData(); // 
	public java.util.List<Long> getChallengerobots(); // 仅仅首次挑战Pvp时有效,其余时候都是空的
	public java.util.List<Long> getChallengerobotsAsData(); // 仅仅首次挑战Pvp时有效,其余时候都是空的
	public int getBechallenging(); // 
	public long getBechallengelockexpiretime(); // 
	public java.util.List<xbean.ArenaFightReport> getFightreports(); // 
	public java.util.List<xbean.ArenaFightReport> getFightreportsAsData(); // 
	public int getBestrank(); // 
	public boolean getHaswin(); // 是否赢过
	public int getFakerank(); // 

	public void setOpen(boolean _v_); // 
	public void setIsrobot(boolean _v_); // 
	public void setLastupdateshengwangtime(long _v_); // 
	public void setBechallenging(int _v_); // 
	public void setBechallengelockexpiretime(long _v_); // 
	public void setBestrank(int _v_); // 
	public void setHaswin(boolean _v_); // 是否赢过
	public void setFakerank(int _v_); // 
}
