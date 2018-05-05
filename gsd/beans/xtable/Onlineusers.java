package xtable;

// typed table access point
public class Onlineusers {
	Onlineusers() {
	}

	public static xbean.OnlineUser get(Long key) {
		return _Tables_.getInstance().onlineusers.get(key);
	}

	public static xbean.OnlineUser get(Long key, xbean.OnlineUser value) {
		return _Tables_.getInstance().onlineusers.get(key, value);
	}

	public static void insert(Long key, xbean.OnlineUser value) {
		_Tables_.getInstance().onlineusers.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().onlineusers.delete(key);
	}

	public static boolean add(Long key, xbean.OnlineUser value) {
		return _Tables_.getInstance().onlineusers.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().onlineusers.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.OnlineUser> getCache() {
		return _Tables_.getInstance().onlineusers.getCache();
	}

	public static xdb.TTable<Long, xbean.OnlineUser> getTable() {
		return _Tables_.getInstance().onlineusers;
	}

	public static xbean.OnlineUser select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.OnlineUser, xbean.OnlineUser>() {
			public xbean.OnlineUser get(xbean.OnlineUser v) { return v.toData(); }
		});
	}

	public static String selectUsername(Long key) {
		return getTable().select(key, new xdb.TField<xbean.OnlineUser, String>() {
				public String get(xbean.OnlineUser v) { return v.getUsername(); }
			});
	}

	public static Integer selectPlattype(Long key) {
		return getTable().select(key, new xdb.TField<xbean.OnlineUser, Integer>() {
				public Integer get(xbean.OnlineUser v) { return v.getPlattype(); }
			});
	}

	public static String selectDeviceid(Long key) {
		return getTable().select(key, new xdb.TField<xbean.OnlineUser, String>() {
				public String get(xbean.OnlineUser v) { return v.getDeviceid(); }
			});
	}

	public static String selectOs(Long key) {
		return getTable().select(key, new xdb.TField<xbean.OnlineUser, String>() {
				public String get(xbean.OnlineUser v) { return v.getOs(); }
			});
	}

	public static String selectPeer(Long key) {
		return getTable().select(key, new xdb.TField<xbean.OnlineUser, String>() {
				public String get(xbean.OnlineUser v) { return v.getPeer(); }
			});
	}

	public static Long selectLogintime(Long key) {
		return getTable().select(key, new xdb.TField<xbean.OnlineUser, Long>() {
				public Long get(xbean.OnlineUser v) { return v.getLogintime(); }
			});
	}

	public static String selectPlatform(Long key) {
		return getTable().select(key, new xdb.TField<xbean.OnlineUser, String>() {
				public String get(xbean.OnlineUser v) { return v.getPlatform(); }
			});
	}

}
