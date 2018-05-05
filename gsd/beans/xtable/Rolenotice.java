package xtable;

// typed table access point
public class Rolenotice {
	Rolenotice() {
	}

	public static xbean.RoleNotice get(Long key) {
		return _Tables_.getInstance().rolenotice.get(key);
	}

	public static xbean.RoleNotice get(Long key, xbean.RoleNotice value) {
		return _Tables_.getInstance().rolenotice.get(key, value);
	}

	public static void insert(Long key, xbean.RoleNotice value) {
		_Tables_.getInstance().rolenotice.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().rolenotice.delete(key);
	}

	public static boolean add(Long key, xbean.RoleNotice value) {
		return _Tables_.getInstance().rolenotice.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().rolenotice.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.RoleNotice> getCache() {
		return _Tables_.getInstance().rolenotice.getCache();
	}

	public static xdb.TTable<Long, xbean.RoleNotice> getTable() {
		return _Tables_.getInstance().rolenotice;
	}

	public static xbean.RoleNotice select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleNotice, xbean.RoleNotice>() {
			public xbean.RoleNotice get(xbean.RoleNotice v) { return v.toData(); }
		});
	}

	public static java.util.List<xbean.Notice> selectNotices(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleNotice, java.util.List<xbean.Notice>>() {
				public java.util.List<xbean.Notice> get(xbean.RoleNotice v) { return v.getNoticesAsData(); }
			});
	}

}
