
package xbean;

public interface FamilyJobStaffList extends xdb.Bean {
	public FamilyJobStaffList copy(); // deep clone
	public FamilyJobStaffList toData(); // a Data instance
	public FamilyJobStaffList toBean(); // a Bean instance
	public FamilyJobStaffList toDataIf(); // a Data instance If need. else return this
	public FamilyJobStaffList toBeanIf(); // a Bean instance If need. else return this

	public java.util.Map<Long, Long> getStaffs(); // key为角色id，long为成为该职位的时间
	public java.util.Map<Long, Long> getStaffsAsData(); // key为角色id，long为成为该职位的时间

}
