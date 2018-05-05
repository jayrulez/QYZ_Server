
package xbean;

public interface RoleLimit extends xdb.Bean {
	public RoleLimit copy(); // deep clone
	public RoleLimit toData(); // a Data instance
	public RoleLimit toBean(); // a Bean instance
	public RoleLimit toDataIf(); // a Data instance If need. else return this
	public RoleLimit toBeanIf(); // a Bean instance If need. else return this

	public java.util.Map<Long, xbean.Limit> getLimits(); // key->(moduleid, eventid)
	public java.util.Map<Long, xbean.Limit> getLimitsAsData(); // key->(moduleid, eventid)
	public java.util.Map<Long, xbean.CoolDown> getCooldowns(); // key->(moduleid, eventid)
	public java.util.Map<Long, xbean.CoolDown> getCooldownsAsData(); // key->(moduleid, eventid)

}
