
package xbean;

public interface OnlineUser extends xdb.Bean {
	public OnlineUser copy(); // deep clone
	public OnlineUser toData(); // a Data instance
	public OnlineUser toBean(); // a Bean instance
	public OnlineUser toDataIf(); // a Data instance If need. else return this
	public OnlineUser toBeanIf(); // a Bean instance If need. else return this

	public String getUsername(); // 
	public com.goldhuman.Common.Octets getUsernameOctets(); // 
	public int getPlattype(); // 
	public String getDeviceid(); // 
	public com.goldhuman.Common.Octets getDeviceidOctets(); // 
	public String getOs(); // 
	public com.goldhuman.Common.Octets getOsOctets(); // 
	public String getPeer(); // 
	public com.goldhuman.Common.Octets getPeerOctets(); // 
	public long getLogintime(); // 
	public String getPlatform(); // 
	public com.goldhuman.Common.Octets getPlatformOctets(); // 

	public void setUsername(String _v_); // 
	public void setUsernameOctets(com.goldhuman.Common.Octets _v_); // 
	public void setPlattype(int _v_); // 
	public void setDeviceid(String _v_); // 
	public void setDeviceidOctets(com.goldhuman.Common.Octets _v_); // 
	public void setOs(String _v_); // 
	public void setOsOctets(com.goldhuman.Common.Octets _v_); // 
	public void setPeer(String _v_); // 
	public void setPeerOctets(com.goldhuman.Common.Octets _v_); // 
	public void setLogintime(long _v_); // 
	public void setPlatform(String _v_); // 
	public void setPlatformOctets(com.goldhuman.Common.Octets _v_); // 
}
