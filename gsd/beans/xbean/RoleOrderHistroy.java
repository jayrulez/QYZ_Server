
package xbean;

public interface RoleOrderHistroy extends xdb.Bean {
	public RoleOrderHistroy copy(); // deep clone
	public RoleOrderHistroy toData(); // a Data instance
	public RoleOrderHistroy toBean(); // a Bean instance
	public RoleOrderHistroy toDataIf(); // a Data instance If need. else return this
	public RoleOrderHistroy toBeanIf(); // a Bean instance If need. else return this

	public java.util.Map<Long, xbean.AppOrder> getSucceedorder(); // 
	public java.util.Map<Long, xbean.AppOrder> getSucceedorderAsData(); // 
	public java.util.Map<Long, xbean.AppOrder> getTimeoutorder(); // 
	public java.util.Map<Long, xbean.AppOrder> getTimeoutorderAsData(); // 

}
