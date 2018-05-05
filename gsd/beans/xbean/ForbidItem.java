
package xbean;

public interface ForbidItem extends xdb.Bean {
	public ForbidItem copy(); // deep clone
	public ForbidItem toData(); // a Data instance
	public ForbidItem toBean(); // a Bean instance
	public ForbidItem toDataIf(); // a Data instance If need. else return this
	public ForbidItem toBeanIf(); // a Bean instance If need. else return this

	public long getForbidtimeinterval(); // 
	public long getForbidrealsetime(); // 
	public String getDesc(); // 
	public com.goldhuman.Common.Octets getDescOctets(); // 
	public String getNotifytouser(); // 
	public com.goldhuman.Common.Octets getNotifytouserOctets(); // 

	public void setForbidtimeinterval(long _v_); // 
	public void setForbidrealsetime(long _v_); // 
	public void setDesc(String _v_); // 
	public void setDescOctets(com.goldhuman.Common.Octets _v_); // 
	public void setNotifytouser(String _v_); // 
	public void setNotifytouserOctets(com.goldhuman.Common.Octets _v_); // 
}
