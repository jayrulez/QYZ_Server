
package xbean;

public interface RoleExchange extends xdb.Bean {
	public RoleExchange copy(); // deep clone
	public RoleExchange toData(); // a Data instance
	public RoleExchange toBean(); // a Bean instance
	public RoleExchange toDataIf(); // a Data instance If need. else return this
	public RoleExchange toBeanIf(); // a Bean instance If need. else return this

	public java.util.List<Long> getItems(); // exchid
	public java.util.List<Long> getItemsAsData(); // exchid
	public java.util.List<Long> getLogs(); // logid
	public java.util.List<Long> getLogsAsData(); // logid

}
