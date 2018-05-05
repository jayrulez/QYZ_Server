
package xbean;

public interface ExchangeLog extends xdb.Bean {
	public ExchangeLog copy(); // deep clone
	public ExchangeLog toData(); // a Data instance
	public ExchangeLog toBean(); // a Bean instance
	public ExchangeLog toDataIf(); // a Data instance If need. else return this
	public ExchangeLog toBeanIf(); // a Bean instance If need. else return this

	public long getSeller(); // 
	public long getBuyer(); // 
	public xbean.ExchangeItem getItem(); // 
	public long getTime(); // 

	public void setSeller(long _v_); // 
	public void setBuyer(long _v_); // 
	public void setTime(long _v_); // 
}
