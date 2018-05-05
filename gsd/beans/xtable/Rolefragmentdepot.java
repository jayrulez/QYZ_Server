package xtable;

// typed table access point
public class Rolefragmentdepot {
	Rolefragmentdepot() {
	}

	public static xbean.RoleFragmentDepot get(Long key) {
		return _Tables_.getInstance().rolefragmentdepot.get(key);
	}

	public static xbean.RoleFragmentDepot get(Long key, xbean.RoleFragmentDepot value) {
		return _Tables_.getInstance().rolefragmentdepot.get(key, value);
	}

	public static void insert(Long key, xbean.RoleFragmentDepot value) {
		_Tables_.getInstance().rolefragmentdepot.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().rolefragmentdepot.delete(key);
	}

	public static boolean add(Long key, xbean.RoleFragmentDepot value) {
		return _Tables_.getInstance().rolefragmentdepot.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().rolefragmentdepot.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.RoleFragmentDepot> getCache() {
		return _Tables_.getInstance().rolefragmentdepot.getCache();
	}

	public static xdb.TTable<Long, xbean.RoleFragmentDepot> getTable() {
		return _Tables_.getInstance().rolefragmentdepot;
	}

	public static xbean.RoleFragmentDepot select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleFragmentDepot, xbean.RoleFragmentDepot>() {
			public xbean.RoleFragmentDepot get(xbean.RoleFragmentDepot v) { return v.toData(); }
		});
	}

	public static Integer selectCapacity(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleFragmentDepot, Integer>() {
				public Integer get(xbean.RoleFragmentDepot v) { return v.getCapacity(); }
			});
	}

	public static java.util.Map<Integer, xbean.Fragment> selectFragments(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleFragmentDepot, java.util.Map<Integer, xbean.Fragment>>() {
				public java.util.Map<Integer, xbean.Fragment> get(xbean.RoleFragmentDepot v) { return v.getFragmentsAsData(); }
			});
	}

}
