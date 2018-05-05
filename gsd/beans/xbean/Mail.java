
package xbean;

public interface Mail extends xdb.Bean {
	public Mail copy(); // deep clone
	public Mail toData(); // a Data instance
	public Mail toBean(); // a Bean instance
	public Mail toDataIf(); // a Data instance If need. else return this
	public Mail toBeanIf(); // a Bean instance If need. else return this

	public int getMailid(); // cfg.mail.Mail
	public long getSendtime(); // 
	public String getTitle(); // 
	public com.goldhuman.Common.Octets getTitleOctets(); // 
	public String getContent(); // 
	public com.goldhuman.Common.Octets getContentOctets(); // 
	public int getRead(); // 
	public xbean.Bonus getAccessory(); // 
	public java.util.List<String> getParams(); // 
	public java.util.List<String> getParamsAsData(); // 

	public void setMailid(int _v_); // cfg.mail.Mail
	public void setSendtime(long _v_); // 
	public void setTitle(String _v_); // 
	public void setTitleOctets(com.goldhuman.Common.Octets _v_); // 
	public void setContent(String _v_); // 
	public void setContentOctets(com.goldhuman.Common.Octets _v_); // 
	public void setRead(int _v_); // 
}
