
package xbean;

public interface IntSet extends xdb.Bean {
	public IntSet copy(); // deep clone
	public IntSet toData(); // a Data instance
	public IntSet toBean(); // a Bean instance
	public IntSet toDataIf(); // a Data instance If need. else return this
	public IntSet toBeanIf(); // a Bean instance If need. else return this

	public java.util.Set<Integer> getVal(); // 
	public java.util.Set<Integer> getValAsData(); // 

}
