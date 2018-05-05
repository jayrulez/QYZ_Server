
package xbean;

public interface GlobalActivationCode extends xdb.Bean {
	public GlobalActivationCode copy(); // deep clone
	public GlobalActivationCode toData(); // a Data instance
	public GlobalActivationCode toBean(); // a Bean instance
	public GlobalActivationCode toDataIf(); // a Data instance If need. else return this
	public GlobalActivationCode toBeanIf(); // a Bean instance If need. else return this

	public java.util.Set<Integer> getAlltypes(); // 
	public java.util.Set<Integer> getAlltypesAsData(); // 

}
