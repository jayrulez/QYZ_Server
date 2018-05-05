package xtable;

// typed table access point
public class Roleallactivity {
	Roleallactivity() {
	}

	public static xbean.RoleAllActivity get(Long key) {
		return _Tables_.getInstance().roleallactivity.get(key);
	}

	public static xbean.RoleAllActivity get(Long key, xbean.RoleAllActivity value) {
		return _Tables_.getInstance().roleallactivity.get(key, value);
	}

	public static void insert(Long key, xbean.RoleAllActivity value) {
		_Tables_.getInstance().roleallactivity.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().roleallactivity.delete(key);
	}

	public static boolean add(Long key, xbean.RoleAllActivity value) {
		return _Tables_.getInstance().roleallactivity.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().roleallactivity.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.RoleAllActivity> getCache() {
		return _Tables_.getInstance().roleallactivity.getCache();
	}

	public static xdb.TTable<Long, xbean.RoleAllActivity> getTable() {
		return _Tables_.getInstance().roleallactivity;
	}

	public static xbean.RoleAllActivity select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleAllActivity, xbean.RoleAllActivity>() {
			public xbean.RoleAllActivity get(xbean.RoleAllActivity v) { return v.toData(); }
		});
	}

	public static java.util.Map<Integer, xbean.RoleActivityStatus> selectStatus(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleAllActivity, java.util.Map<Integer, xbean.RoleActivityStatus>>() {
				public java.util.Map<Integer, xbean.RoleActivityStatus> get(xbean.RoleAllActivity v) { return v.getStatusAsData(); }
			});
	}

	public static java.util.Map<Integer, xbean.RoleActivityRecordMap> selectRecords(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleAllActivity, java.util.Map<Integer, xbean.RoleActivityRecordMap>>() {
				public java.util.Map<Integer, xbean.RoleActivityRecordMap> get(xbean.RoleAllActivity v) { return v.getRecordsAsData(); }
			});
	}

}
