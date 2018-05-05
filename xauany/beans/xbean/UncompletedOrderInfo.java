
package xbean;

public interface UncompletedOrderInfo extends xdb.Bean {
	public UncompletedOrderInfo copy(); // deep clone
	public UncompletedOrderInfo toData(); // a Data instance
	public UncompletedOrderInfo toBean(); // a Bean instance
	public UncompletedOrderInfo toDataIf(); // a Data instance If need. else return this
	public UncompletedOrderInfo toBeanIf(); // a Bean instance If need. else return this

	public int getServerid(); // 
	public int getPlattype(); // 
	public String getPlatorderid(); // 
	public com.goldhuman.Common.Octets getPlatorderidOctets(); // 
	public long getUserid(); // 
	public <T extends com.goldhuman.Common.Marshal.Marshal> T getVars(T _v_); // 
	public boolean isVarsEmpty(); // 
	public byte[] getVarsCopy(); // 
	public int getTimes(); // 

	public void setServerid(int _v_); // 
	public void setPlattype(int _v_); // 
	public void setPlatorderid(String _v_); // 
	public void setPlatorderidOctets(com.goldhuman.Common.Octets _v_); // 
	public void setUserid(long _v_); // 
	public void setVars(com.goldhuman.Common.Marshal.Marshal _v_); // 
	public void setVarsCopy(byte[] _v_); // 
	public void setTimes(int _v_); // 
}
