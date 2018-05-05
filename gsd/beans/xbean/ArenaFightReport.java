
package xbean;

public interface ArenaFightReport extends xdb.Bean {
	public ArenaFightReport copy(); // deep clone
	public ArenaFightReport toData(); // a Data instance
	public ArenaFightReport toBean(); // a Bean instance
	public ArenaFightReport toDataIf(); // a Data instance If need. else return this
	public ArenaFightReport toBeanIf(); // a Bean instance If need. else return this

	public long getFighttime(); // 
	public int getChallengetype(); // 
	public int getSucc(); // 
	public int getNewrank(); // 
	public String getOpponentname(); // 
	public com.goldhuman.Common.Octets getOpponentnameOctets(); // 
	public int getOldrank(); // 

	public void setFighttime(long _v_); // 
	public void setChallengetype(int _v_); // 
	public void setSucc(int _v_); // 
	public void setNewrank(int _v_); // 
	public void setOpponentname(String _v_); // 
	public void setOpponentnameOctets(com.goldhuman.Common.Octets _v_); // 
	public void setOldrank(int _v_); // 
}
