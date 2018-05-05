
package xbean;

public interface Dress extends xdb.Bean {
	public Dress copy(); // deep clone
	public Dress toData(); // a Data instance
	public Dress toBean(); // a Bean instance
	public Dress toDataIf(); // a Data instance If need. else return this
	public Dress toBeanIf(); // a Bean instance If need. else return this

	public int getModelid(); // 
	public long getExpiretime(); // 过期时间

	public void setModelid(int _v_); // 
	public void setExpiretime(long _v_); // 过期时间
}
