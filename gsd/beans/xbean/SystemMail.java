
package xbean;

public interface SystemMail extends xdb.Bean {
	public SystemMail copy(); // deep clone
	public SystemMail toData(); // a Data instance
	public SystemMail toBean(); // a Bean instance
	public SystemMail toDataIf(); // a Data instance If need. else return this
	public SystemMail toBeanIf(); // a Bean instance If need. else return this

	public long getId(); // 
	public int getMailid(); // cfg.mail.Mail
	public long getSendtime(); // 
	public String getTitle(); // 
	public com.goldhuman.Common.Octets getTitleOctets(); // 
	public String getContent(); // 
	public com.goldhuman.Common.Octets getContentOctets(); // 
	public xbean.Bonus getBonus(); // 
	public java.util.List<String> getParams(); // 
	public java.util.List<String> getParamsAsData(); // 
	public java.util.Set<Long> getRecords(); // 已领奖玩家id
	public java.util.Set<Long> getRecordsAsData(); // 已领奖玩家id

	public void setId(long _v_); // 
	public void setMailid(int _v_); // cfg.mail.Mail
	public void setSendtime(long _v_); // 
	public void setTitle(String _v_); // 
	public void setTitleOctets(com.goldhuman.Common.Octets _v_); // 
	public void setContent(String _v_); // 
	public void setContentOctets(com.goldhuman.Common.Octets _v_); // 
}
