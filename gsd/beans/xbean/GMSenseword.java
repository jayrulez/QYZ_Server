
package xbean;

public interface GMSenseword extends xdb.Bean {
	public GMSenseword copy(); // deep clone
	public GMSenseword toData(); // a Data instance
	public GMSenseword toBean(); // a Bean instance
	public GMSenseword toDataIf(); // a Data instance If need. else return this
	public GMSenseword toBeanIf(); // a Bean instance If need. else return this

	public java.util.Set<String> getAddwords(); // 
	public java.util.Set<String> getAddwordsAsData(); // 
	public java.util.Set<String> getRemovewords(); // 
	public java.util.Set<String> getRemovewordsAsData(); // 

}
