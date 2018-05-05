
package xbean;

public interface RoleActivityRecordMap extends xdb.Bean {
	public RoleActivityRecordMap copy(); // deep clone
	public RoleActivityRecordMap toData(); // a Data instance
	public RoleActivityRecordMap toBean(); // a Bean instance
	public RoleActivityRecordMap toDataIf(); // a Data instance If need. else return this
	public RoleActivityRecordMap toBeanIf(); // a Bean instance If need. else return this

	public java.util.Map<Long, xbean.RoleActivityRecord> getRecords(); // 活动数据
	public java.util.Map<Long, xbean.RoleActivityRecord> getRecordsAsData(); // 活动数据

}
