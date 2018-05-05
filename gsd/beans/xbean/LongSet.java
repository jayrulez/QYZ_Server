
package xbean;

public interface LongSet extends xdb.Bean {
	public LongSet copy(); // deep clone
	public LongSet toData(); // a Data instance
	public LongSet toBean(); // a Bean instance
	public LongSet toDataIf(); // a Data instance If need. else return this
	public LongSet toBeanIf(); // a Bean instance If need. else return this

	public java.util.Set<Long> getVal(); // 
	public java.util.Set<Long> getValAsData(); // 

}
