
package xbean;

public interface CoolDown extends xdb.Bean {
	public CoolDown copy(); // deep clone
	public CoolDown toData(); // a Data instance
	public CoolDown toBean(); // a Bean instance
	public CoolDown toDataIf(); // a Data instance If need. else return this
	public CoolDown toBeanIf(); // a Bean instance If need. else return this

	public long getId(); // 
	public long getExpiretime(); // 

	public void setId(long _v_); // 
	public void setExpiretime(long _v_); // 
}
