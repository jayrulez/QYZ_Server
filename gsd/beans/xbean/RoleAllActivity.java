
package xbean;

public interface RoleAllActivity extends xdb.Bean {
	public RoleAllActivity copy(); // deep clone
	public RoleAllActivity toData(); // a Data instance
	public RoleAllActivity toBean(); // a Bean instance
	public RoleAllActivity toDataIf(); // a Data instance If need. else return this
	public RoleAllActivity toBeanIf(); // a Bean instance If need. else return this

	public java.util.Map<Integer, xbean.RoleActivityStatus> getStatus(); // 每个活动的状态
	public java.util.Map<Integer, xbean.RoleActivityStatus> getStatusAsData(); // 每个活动的状态
	public java.util.Map<Integer, xbean.RoleActivityRecordMap> getRecords(); // 活动数据
	public java.util.Map<Integer, xbean.RoleActivityRecordMap> getRecordsAsData(); // 活动数据

}
