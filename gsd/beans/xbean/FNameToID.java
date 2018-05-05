
package xbean;

public interface FNameToID extends xdb.Bean {
	public FNameToID copy(); // deep clone
	public FNameToID toData(); // a Data instance
	public FNameToID toBean(); // a Bean instance
	public FNameToID toDataIf(); // a Data instance If need. else return this
	public FNameToID toBeanIf(); // a Bean instance If need. else return this

	public java.util.Map<String, Long> getData(); // 
	public java.util.Map<String, Long> getDataAsData(); // 

}
