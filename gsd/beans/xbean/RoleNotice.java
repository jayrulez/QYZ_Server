
package xbean;

public interface RoleNotice extends xdb.Bean {
	public RoleNotice copy(); // deep clone
	public RoleNotice toData(); // a Data instance
	public RoleNotice toBean(); // a Bean instance
	public RoleNotice toDataIf(); // a Data instance If need. else return this
	public RoleNotice toBeanIf(); // a Bean instance If need. else return this

	public java.util.List<xbean.Notice> getNotices(); // 
	public java.util.List<xbean.Notice> getNoticesAsData(); // 

}
