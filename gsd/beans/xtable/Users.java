package xtable;

// typed table access point
public class Users {
	Users() {
	}

	public static xbean.User get(Long key) {
		return _Tables_.getInstance().users.get(key);
	}

	public static xbean.User get(Long key, xbean.User value) {
		return _Tables_.getInstance().users.get(key, value);
	}

	public static void insert(Long key, xbean.User value) {
		_Tables_.getInstance().users.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().users.delete(key);
	}

	public static boolean add(Long key, xbean.User value) {
		return _Tables_.getInstance().users.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().users.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.User> getCache() {
		return _Tables_.getInstance().users.getCache();
	}

	public static xdb.TTable<Long, xbean.User> getTable() {
		return _Tables_.getInstance().users;
	}

	public static xbean.User select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.User, xbean.User>() {
			public xbean.User get(xbean.User v) { return v.toData(); }
		});
	}

	public static java.util.List<Long> selectRoleids(Long key) {
		return getTable().select(key, new xdb.TField<xbean.User, java.util.List<Long>>() {
				public java.util.List<Long> get(xbean.User v) { return v.getRoleidsAsData(); }
			});
	}

	public static Long selectLastloginrole(Long key) {
		return getTable().select(key, new xdb.TField<xbean.User, Long>() {
				public Long get(xbean.User v) { return v.getLastloginrole(); }
			});
	}

	public static Integer selectDeletedroles(Long key) {
		return getTable().select(key, new xdb.TField<xbean.User, Integer>() {
				public Integer get(xbean.User v) { return v.getDeletedroles(); }
			});
	}

	public static java.util.Map<Long, Long> selectDeleteinfo(Long key) {
		return getTable().select(key, new xdb.TField<xbean.User, java.util.Map<Long, Long>>() {
				public java.util.Map<Long, Long> get(xbean.User v) { return v.getDeleteinfoAsData(); }
			});
	}

}
