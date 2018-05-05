
package xbean;

public interface OnesdkUserInfo extends xdb.Bean {
	public OnesdkUserInfo copy(); // deep clone
	public OnesdkUserInfo toData(); // a Data instance
	public OnesdkUserInfo toBean(); // a Bean instance
	public OnesdkUserInfo toDataIf(); // a Data instance If need. else return this
	public OnesdkUserInfo toBeanIf(); // a Bean instance If need. else return this

	public long getUserid(); // 
	public long getUserinfoid(); // 

	public void setUserid(long _v_); // 
	public void setUserinfoid(long _v_); // 
}
