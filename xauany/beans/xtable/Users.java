package xtable;

// typed table access point
public class Users {
	Users() {
	}

	public static xdb.util.AutoKey<Long> getAutoKey() {
		return _Tables_.getInstance().users.getAutoKey();
	}

	public static Long nextKey() {
		return getAutoKey().next();
	}

	public static Long insert(xbean.UserInfo value) {
		Long next = nextKey();
		insert(next, value);
		return next;
	}

	public static xbean.UserInfo get(Long key) {
		return _Tables_.getInstance().users.get(key);
	}

	public static xbean.UserInfo get(Long key, xbean.UserInfo value) {
		return _Tables_.getInstance().users.get(key, value);
	}

	public static void insert(Long key, xbean.UserInfo value) {
		_Tables_.getInstance().users.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().users.delete(key);
	}

	public static boolean add(Long key, xbean.UserInfo value) {
		return _Tables_.getInstance().users.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().users.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.UserInfo> getCache() {
		return _Tables_.getInstance().users.getCache();
	}

	public static xdb.TTable<Long, xbean.UserInfo> getTable() {
		return _Tables_.getInstance().users;
	}

	public static xbean.UserInfo select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.UserInfo, xbean.UserInfo>() {
			public xbean.UserInfo get(xbean.UserInfo v) { return v.toData(); }
		});
	}

	public static Integer selectPlattype(Long key) {
		return getTable().select(key, new xdb.TField<xbean.UserInfo, Integer>() {
				public Integer get(xbean.UserInfo v) { return v.getPlattype(); }
			});
	}

	public static String selectUseridentity(Long key) {
		return getTable().select(key, new xdb.TField<xbean.UserInfo, String>() {
				public String get(xbean.UserInfo v) { return v.getUseridentity(); }
			});
	}

}
