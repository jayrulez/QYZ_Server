
package xbean;

public interface LongList extends xdb.Bean {
	public LongList copy(); // deep clone
	public LongList toData(); // a Data instance
	public LongList toBean(); // a Bean instance
	public LongList toDataIf(); // a Data instance If need. else return this
	public LongList toBeanIf(); // a Bean instance If need. else return this

	public java.util.List<Long> getVal(); // 
	public java.util.List<Long> getValAsData(); // 

}
