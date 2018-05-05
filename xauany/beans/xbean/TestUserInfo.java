
package xbean;

public interface TestUserInfo extends xdb.Bean {
	public TestUserInfo copy(); // deep clone
	public TestUserInfo toData(); // a Data instance
	public TestUserInfo toBean(); // a Bean instance
	public TestUserInfo toDataIf(); // a Data instance If need. else return this
	public TestUserInfo toBeanIf(); // a Bean instance If need. else return this

	public long getUserid(); // 
	public long getUserinfoid(); // 

	public void setUserid(long _v_); // 
	public void setUserinfoid(long _v_); // 
}
