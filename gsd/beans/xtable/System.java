package xtable;

// typed table access point
public class System {
	System() {
	}

	public static xbean.System get(Integer key) {
		return _Tables_.getInstance().system.get(key);
	}

	public static xbean.System get(Integer key, xbean.System value) {
		return _Tables_.getInstance().system.get(key, value);
	}

	public static void insert(Integer key, xbean.System value) {
		_Tables_.getInstance().system.insert(key, value);
	}

	public static void delete(Integer key) {
		_Tables_.getInstance().system.delete(key);
	}

	public static boolean add(Integer key, xbean.System value) {
		return _Tables_.getInstance().system.add(key, value);
	}

	public static boolean remove(Integer key) {
		return _Tables_.getInstance().system.remove(key);
	}

	public static xdb.TTableCache<Integer, xbean.System> getCache() {
		return _Tables_.getInstance().system.getCache();
	}

	public static xdb.TTable<Integer, xbean.System> getTable() {
		return _Tables_.getInstance().system;
	}

	public static xbean.System select(Integer key) {
		return getTable().select(key, new xdb.TField<xbean.System, xbean.System>() {
			public xbean.System get(xbean.System v) { return v.toData(); }
		});
	}

	public static Boolean selectInitrobot(Integer key) {
		return getTable().select(key, new xdb.TField<xbean.System, Boolean>() {
				public Boolean get(xbean.System v) { return v.getInitrobot(); }
			});
	}

	public static java.util.List<Long> selectOutrankrobots(Integer key) {
		return getTable().select(key, new xdb.TField<xbean.System, java.util.List<Long>>() {
				public java.util.List<Long> get(xbean.System v) { return v.getOutrankrobotsAsData(); }
			});
	}

	public static Long selectGsdfirststarttime(Integer key) {
		return getTable().select(key, new xdb.TField<xbean.System, Long>() {
				public Long get(xbean.System v) { return v.getGsdfirststarttime(); }
			});
	}

	public static Integer selectRolenumsreach20(Integer key) {
		return getTable().select(key, new xdb.TField<xbean.System, Integer>() {
				public Integer get(xbean.System v) { return v.getRolenumsreach20(); }
			});
	}

	public static Integer selectMaxsystemfamnum(Integer key) {
		return getTable().select(key, new xdb.TField<xbean.System, Integer>() {
				public Integer get(xbean.System v) { return v.getMaxsystemfamnum(); }
			});
	}

}
