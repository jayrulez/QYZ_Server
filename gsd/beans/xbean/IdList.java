
package xbean;

public interface IdList extends xdb.Bean {
	public IdList copy(); // deep clone
	public IdList toData(); // a Data instance
	public IdList toBean(); // a Bean instance
	public IdList toDataIf(); // a Data instance If need. else return this
	public IdList toBeanIf(); // a Bean instance If need. else return this

	public java.util.List<Long> getLists(); // 
	public java.util.List<Long> getListsAsData(); // 

}
