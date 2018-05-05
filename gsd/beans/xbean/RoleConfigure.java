
package xbean;

public interface RoleConfigure extends xdb.Bean {
	public RoleConfigure copy(); // deep clone
	public RoleConfigure toData(); // a Data instance
	public RoleConfigure toBean(); // a Bean instance
	public RoleConfigure toDataIf(); // a Data instance If need. else return this
	public RoleConfigure toBeanIf(); // a Bean instance If need. else return this

	public java.util.Map<String, String> getDatas(); // 
	public java.util.Map<String, String> getDatasAsData(); // 

}
