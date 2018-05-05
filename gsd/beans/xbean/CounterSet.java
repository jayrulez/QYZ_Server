
package xbean;

public interface CounterSet extends xdb.Bean {
	public CounterSet copy(); // deep clone
	public CounterSet toData(); // a Data instance
	public CounterSet toBean(); // a Bean instance
	public CounterSet toDataIf(); // a Data instance If need. else return this
	public CounterSet toBeanIf(); // a Bean instance If need. else return this

	public java.util.Set<Long> getItems(); // 
	public java.util.Set<Long> getItemsAsData(); // 

}
