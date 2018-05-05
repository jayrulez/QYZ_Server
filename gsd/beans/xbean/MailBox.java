
package xbean;

public interface MailBox extends xdb.Bean {
	public MailBox copy(); // deep clone
	public MailBox toData(); // a Data instance
	public MailBox toBean(); // a Bean instance
	public MailBox toDataIf(); // a Data instance If need. else return this
	public MailBox toBeanIf(); // a Bean instance If need. else return this

	public int getNextmailid(); // 
	public java.util.Map<Integer, xbean.Mail> getMails(); // 
	public java.util.Map<Integer, xbean.Mail> getMailsAsData(); // 
	public long getMaxsysmail(); // 已经领取的最大的系统邮件的id

	public void setNextmailid(int _v_); // 
	public void setMaxsysmail(long _v_); // 已经领取的最大的系统邮件的id
}
