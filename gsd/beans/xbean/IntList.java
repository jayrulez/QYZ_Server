
package xbean;

public interface IntList extends xdb.Bean {
	public IntList copy(); // deep clone
	public IntList toData(); // a Data instance
	public IntList toBean(); // a Bean instance
	public IntList toDataIf(); // a Data instance If need. else return this
	public IntList toBeanIf(); // a Bean instance If need. else return this

	public java.util.List<Integer> getVal(); // 
	public java.util.List<Integer> getValAsData(); // 

}
