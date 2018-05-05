
package xbean;

public interface UserInfo extends xdb.Bean {
	public UserInfo copy(); // deep clone
	public UserInfo toData(); // a Data instance
	public UserInfo toBean(); // a Bean instance
	public UserInfo toDataIf(); // a Data instance If need. else return this
	public UserInfo toBeanIf(); // a Bean instance If need. else return this

	public int getPlattype(); // 平台id
	public String getUseridentity(); // 平台用户id
	public com.goldhuman.Common.Octets getUseridentityOctets(); // 平台用户id

	public void setPlattype(int _v_); // 平台id
	public void setUseridentity(String _v_); // 平台用户id
	public void setUseridentityOctets(com.goldhuman.Common.Octets _v_); // 平台用户id
}
