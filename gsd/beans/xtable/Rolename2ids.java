package xtable;

// typed table access point
public class Rolename2ids {
	Rolename2ids() {
	}

	public static Long get(String key) {
		return _Tables_.getInstance().rolename2ids.get(key);
	}

	public static Long get(String key, Long value) {
		return _Tables_.getInstance().rolename2ids.get(key, value);
	}

	public static void insert(String key, Long value) {
		_Tables_.getInstance().rolename2ids.insert(key, value);
	}

	public static void delete(String key) {
		_Tables_.getInstance().rolename2ids.delete(key);
	}

	public static boolean add(String key, Long value) {
		return _Tables_.getInstance().rolename2ids.add(key, value);
	}

	public static boolean remove(String key) {
		return _Tables_.getInstance().rolename2ids.remove(key);
	}

	public static xdb.TTableCache<String, Long> getCache() {
		return _Tables_.getInstance().rolename2ids.getCache();
	}

	public static xdb.TTable<String, Long> getTable() {
		return _Tables_.getInstance().rolename2ids;
	}

	public static Long select(String key) {
		return getTable().select(key, new xdb.TField<Long, Long>() {
			public Long get(Long v) { return v; }
		});
	}

}
