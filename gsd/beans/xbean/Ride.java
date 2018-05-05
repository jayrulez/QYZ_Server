
package xbean;

public interface Ride extends xdb.Bean {
	public Ride copy(); // deep clone
	public Ride toData(); // a Data instance
	public Ride toBean(); // a Bean instance
	public Ride toDataIf(); // a Data instance If need. else return this
	public Ride toBeanIf(); // a Bean instance If need. else return this

	public int getModelid(); // 
	public long getExpiretime(); // 过期时间

	public void setModelid(int _v_); // 
	public void setExpiretime(long _v_); // 过期时间
}
