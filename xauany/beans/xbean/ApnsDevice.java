
package xbean;

public interface ApnsDevice extends xdb.Bean {
	public ApnsDevice copy(); // deep clone
	public ApnsDevice toData(); // a Data instance
	public ApnsDevice toBean(); // a Bean instance
	public ApnsDevice toDataIf(); // a Data instance If need. else return this
	public ApnsDevice toBeanIf(); // a Bean instance If need. else return this

	public String getToken(); // 
	public com.goldhuman.Common.Octets getTokenOctets(); // 
	public long getUpdatetime(); // 

	public void setToken(String _v_); // 
	public void setTokenOctets(com.goldhuman.Common.Octets _v_); // 
	public void setUpdatetime(long _v_); // 
}
