package xtable;

// typed table access point
public class Onesdkusers {
	Onesdkusers() {
	}

	public static xbean.OnesdkUserInfo get(String key) {
		return _Tables_.getInstance().onesdkusers.get(key);
	}

	public static xbean.OnesdkUserInfo get(String key, xbean.OnesdkUserInfo value) {
		return _Tables_.getInstance().onesdkusers.get(key, value);
	}

	public static void insert(String key, xbean.OnesdkUserInfo value) {
		_Tables_.getInstance().onesdkusers.insert(key, value);
	}

	public static void delete(String key) {
		_Tables_.getInstance().onesdkusers.delete(key);
	}

	public static boolean add(String key, xbean.OnesdkUserInfo value) {
		return _Tables_.getInstance().onesdkusers.add(key, value);
	}

	public static boolean remove(String key) {
		return _Tables_.getInstance().onesdkusers.remove(key);
	}

	public static xdb.TTableCache<String, xbean.OnesdkUserInfo> getCache() {
		return _Tables_.getInstance().onesdkusers.getCache();
	}

	public static xdb.TTable<String, xbean.OnesdkUserInfo> getTable() {
		return _Tables_.getInstance().onesdkusers;
	}

	public static xbean.OnesdkUserInfo select(String key) {
		return getTable().select(key, new xdb.TField<xbean.OnesdkUserInfo, xbean.OnesdkUserInfo>() {
			public xbean.OnesdkUserInfo get(xbean.OnesdkUserInfo v) { return v.toData(); }
		});
	}

	public static Long selectUserid(String key) {
		return getTable().select(key, new xdb.TField<xbean.OnesdkUserInfo, Long>() {
				public Long get(xbean.OnesdkUserInfo v) { return v.getUserid(); }
			});
	}

	public static Long selectUserinfoid(String key) {
		return getTable().select(key, new xdb.TField<xbean.OnesdkUserInfo, Long>() {
				public Long get(xbean.OnesdkUserInfo v) { return v.getUserinfoid(); }
			});
	}

}
