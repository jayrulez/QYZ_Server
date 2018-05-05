package xtable;

// typed table access point
public class Userdeletedroles {
	Userdeletedroles() {
	}

	public static xbean.UserDeletedRole get(Long key) {
		return _Tables_.getInstance().userdeletedroles.get(key);
	}

	public static xbean.UserDeletedRole get(Long key, xbean.UserDeletedRole value) {
		return _Tables_.getInstance().userdeletedroles.get(key, value);
	}

	public static void insert(Long key, xbean.UserDeletedRole value) {
		_Tables_.getInstance().userdeletedroles.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().userdeletedroles.delete(key);
	}

	public static boolean add(Long key, xbean.UserDeletedRole value) {
		return _Tables_.getInstance().userdeletedroles.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().userdeletedroles.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.UserDeletedRole> getCache() {
		return _Tables_.getInstance().userdeletedroles.getCache();
	}

	public static xdb.TTable<Long, xbean.UserDeletedRole> getTable() {
		return _Tables_.getInstance().userdeletedroles;
	}

	public static xbean.UserDeletedRole select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.UserDeletedRole, xbean.UserDeletedRole>() {
			public xbean.UserDeletedRole get(xbean.UserDeletedRole v) { return v.toData(); }
		});
	}

	public static java.util.List<xbean.DeletedRole> selectRoles(Long key) {
		return getTable().select(key, new xdb.TField<xbean.UserDeletedRole, java.util.List<xbean.DeletedRole>>() {
				public java.util.List<xbean.DeletedRole> get(xbean.UserDeletedRole v) { return v.getRolesAsData(); }
			});
	}

}
