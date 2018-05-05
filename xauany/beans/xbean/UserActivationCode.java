
package xbean;

public interface UserActivationCode extends xdb.Bean {
	public UserActivationCode copy(); // deep clone
	public UserActivationCode toData(); // a Data instance
	public UserActivationCode toBean(); // a Bean instance
	public UserActivationCode toDataIf(); // a Data instance If need. else return this
	public UserActivationCode toBeanIf(); // a Bean instance If need. else return this

	public java.util.Map<Integer, Long> getAll(); // key:codetype
	public java.util.Map<Integer, Long> getAllAsData(); // key:codetype

}
