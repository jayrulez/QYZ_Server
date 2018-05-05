
package xbean;

public interface OnesdkOrderInfo extends xdb.Bean {
	public OnesdkOrderInfo copy(); // deep clone
	public OnesdkOrderInfo toData(); // a Data instance
	public OnesdkOrderInfo toBean(); // a Bean instance
	public OnesdkOrderInfo toDataIf(); // a Data instance If need. else return this
	public OnesdkOrderInfo toBeanIf(); // a Bean instance If need. else return this

	public long getCreatetime(); // 
	public String getGsorderid(); // 游戏服务器订单id
	public com.goldhuman.Common.Octets getGsorderidOctets(); // 游戏服务器订单id
	public String getUseridentity(); // 平台用户id
	public com.goldhuman.Common.Octets getUseridentityOctets(); // 平台用户id
	public <T extends com.goldhuman.Common.Marshal.Marshal> T getVars(T _v_); // 
	public boolean isVarsEmpty(); // 
	public byte[] getVarsCopy(); // 

	public void setCreatetime(long _v_); // 
	public void setGsorderid(String _v_); // 游戏服务器订单id
	public void setGsorderidOctets(com.goldhuman.Common.Octets _v_); // 游戏服务器订单id
	public void setUseridentity(String _v_); // 平台用户id
	public void setUseridentityOctets(com.goldhuman.Common.Octets _v_); // 平台用户id
	public void setVars(com.goldhuman.Common.Marshal.Marshal _v_); // 
	public void setVarsCopy(byte[] _v_); // 
}
