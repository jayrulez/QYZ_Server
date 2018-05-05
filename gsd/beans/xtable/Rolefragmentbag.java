package xtable;

// typed table access point
public class Rolefragmentbag {
	Rolefragmentbag() {
	}

	public static xbean.RoleFragmentBag get(Long key) {
		return _Tables_.getInstance().rolefragmentbag.get(key);
	}

	public static xbean.RoleFragmentBag get(Long key, xbean.RoleFragmentBag value) {
		return _Tables_.getInstance().rolefragmentbag.get(key, value);
	}

	public static void insert(Long key, xbean.RoleFragmentBag value) {
		_Tables_.getInstance().rolefragmentbag.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().rolefragmentbag.delete(key);
	}

	public static boolean add(Long key, xbean.RoleFragmentBag value) {
		return _Tables_.getInstance().rolefragmentbag.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().rolefragmentbag.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.RoleFragmentBag> getCache() {
		return _Tables_.getInstance().rolefragmentbag.getCache();
	}

	public static xdb.TTable<Long, xbean.RoleFragmentBag> getTable() {
		return _Tables_.getInstance().rolefragmentbag;
	}

	public static xbean.RoleFragmentBag select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleFragmentBag, xbean.RoleFragmentBag>() {
			public xbean.RoleFragmentBag get(xbean.RoleFragmentBag v) { return v.toData(); }
		});
	}

	public static Integer selectCapacity(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleFragmentBag, Integer>() {
				public Integer get(xbean.RoleFragmentBag v) { return v.getCapacity(); }
			});
	}

	public static java.util.Map<Integer, xbean.Fragment> selectItems(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleFragmentBag, java.util.Map<Integer, xbean.Fragment>>() {
				public java.util.Map<Integer, xbean.Fragment> get(xbean.RoleFragmentBag v) { return v.getItemsAsData(); }
			});
	}

}
