
package xbean;

public interface HuiWuChampion extends xdb.Bean {
	public HuiWuChampion copy(); // deep clone
	public HuiWuChampion toData(); // a Data instance
	public HuiWuChampion toBean(); // a Bean instance
	public HuiWuChampion toDataIf(); // a Data instance If need. else return this
	public HuiWuChampion toBeanIf(); // a Bean instance If need. else return this

	public long getRoleid(); // 
	public int getWorshipnum(); // 
	public String getAwardword(); // 
	public com.goldhuman.Common.Octets getAwardwordOctets(); // 

	public void setRoleid(long _v_); // 
	public void setWorshipnum(int _v_); // 
	public void setAwardword(String _v_); // 
	public void setAwardwordOctets(com.goldhuman.Common.Octets _v_); // 
}
