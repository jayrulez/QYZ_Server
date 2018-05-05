
package xbean;

public interface IdGen extends xdb.Bean {
	public IdGen copy(); // deep clone
	public IdGen toData(); // a Data instance
	public IdGen toBean(); // a Bean instance
	public IdGen toDataIf(); // a Data instance If need. else return this
	public IdGen toBeanIf(); // a Bean instance If need. else return this

	public long getItemid(); // 

	public void setItemid(long _v_); // 
}
