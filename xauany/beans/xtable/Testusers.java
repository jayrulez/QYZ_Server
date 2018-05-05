package xtable;

// typed table access point
public class Testusers {
	Testusers() {
	}

	public static xbean.TestUserInfo get(String key) {
		return _Tables_.getInstance().testusers.get(key);
	}

	public static xbean.TestUserInfo get(String key, xbean.TestUserInfo value) {
		return _Tables_.getInstance().testusers.get(key, value);
	}

	public static void insert(String key, xbean.TestUserInfo value) {
		_Tables_.getInstance().testusers.insert(key, value);
	}

	public static void delete(String key) {
		_Tables_.getInstance().testusers.delete(key);
	}

	public static boolean add(String key, xbean.TestUserInfo value) {
		return _Tables_.getInstance().testusers.add(key, value);
	}

	public static boolean remove(String key) {
		return _Tables_.getInstance().testusers.remove(key);
	}

	public static xdb.TTableCache<String, xbean.TestUserInfo> getCache() {
		return _Tables_.getInstance().testusers.getCache();
	}

	public static xdb.TTable<String, xbean.TestUserInfo> getTable() {
		return _Tables_.getInstance().testusers;
	}

	public static xbean.TestUserInfo select(String key) {
		return getTable().select(key, new xdb.TField<xbean.TestUserInfo, xbean.TestUserInfo>() {
			public xbean.TestUserInfo get(xbean.TestUserInfo v) { return v.toData(); }
		});
	}

	public static Long selectUserid(String key) {
		return getTable().select(key, new xdb.TField<xbean.TestUserInfo, Long>() {
				public Long get(xbean.TestUserInfo v) { return v.getUserid(); }
			});
	}

	public static Long selectUserinfoid(String key) {
		return getTable().select(key, new xdb.TField<xbean.TestUserInfo, Long>() {
				public Long get(xbean.TestUserInfo v) { return v.getUserinfoid(); }
			});
	}

}
