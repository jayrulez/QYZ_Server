
package xbean;

public interface UserPayInfo extends xdb.Bean {
	public UserPayInfo copy(); // deep clone
	public UserPayInfo toData(); // a Data instance
	public UserPayInfo toBean(); // a Bean instance
	public UserPayInfo toDataIf(); // a Data instance If need. else return this
	public UserPayInfo toBeanIf(); // a Bean instance If need. else return this

	public long getTotalpay(); // 
	public long getTotalyunbao(); // 
	public long getTotalbindyuanbao(); // 
	public long getTotalvipexp(); // 
	public boolean getHasgotreturn(); // 
	public long getRoleid(); // 

	public void setTotalpay(long _v_); // 
	public void setTotalyunbao(long _v_); // 
	public void setTotalbindyuanbao(long _v_); // 
	public void setTotalvipexp(long _v_); // 
	public void setHasgotreturn(boolean _v_); // 
	public void setRoleid(long _v_); // 
}
