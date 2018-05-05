package xtable;

// typed table access point
public class Robotids {
	Robotids() {
	}

	public static xbean.RobotId get(Integer key) {
		return _Tables_.getInstance().robotids.get(key);
	}

	public static xbean.RobotId get(Integer key, xbean.RobotId value) {
		return _Tables_.getInstance().robotids.get(key, value);
	}

	public static void insert(Integer key, xbean.RobotId value) {
		_Tables_.getInstance().robotids.insert(key, value);
	}

	public static void delete(Integer key) {
		_Tables_.getInstance().robotids.delete(key);
	}

	public static boolean add(Integer key, xbean.RobotId value) {
		return _Tables_.getInstance().robotids.add(key, value);
	}

	public static boolean remove(Integer key) {
		return _Tables_.getInstance().robotids.remove(key);
	}

	public static xdb.TTableCache<Integer, xbean.RobotId> getCache() {
		return _Tables_.getInstance().robotids.getCache();
	}

	public static xdb.TTable<Integer, xbean.RobotId> getTable() {
		return _Tables_.getInstance().robotids;
	}

	public static xbean.RobotId select(Integer key) {
		return getTable().select(key, new xdb.TField<xbean.RobotId, xbean.RobotId>() {
			public xbean.RobotId get(xbean.RobotId v) { return v.toData(); }
		});
	}

	public static Long selectMinuserid(Integer key) {
		return getTable().select(key, new xdb.TField<xbean.RobotId, Long>() {
				public Long get(xbean.RobotId v) { return v.getMinuserid(); }
			});
	}

	public static Long selectMaxuserid(Integer key) {
		return getTable().select(key, new xdb.TField<xbean.RobotId, Long>() {
				public Long get(xbean.RobotId v) { return v.getMaxuserid(); }
			});
	}

}
