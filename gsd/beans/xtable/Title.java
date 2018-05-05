package xtable;

// typed table access point
public class Title {
	Title() {
	}

	public static xbean.RoleTitle get(Long key) {
		return _Tables_.getInstance().title.get(key);
	}

	public static xbean.RoleTitle get(Long key, xbean.RoleTitle value) {
		return _Tables_.getInstance().title.get(key, value);
	}

	public static void insert(Long key, xbean.RoleTitle value) {
		_Tables_.getInstance().title.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().title.delete(key);
	}

	public static boolean add(Long key, xbean.RoleTitle value) {
		return _Tables_.getInstance().title.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().title.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.RoleTitle> getCache() {
		return _Tables_.getInstance().title.getCache();
	}

	public static xdb.TTable<Long, xbean.RoleTitle> getTable() {
		return _Tables_.getInstance().title;
	}

	public static xbean.RoleTitle select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleTitle, xbean.RoleTitle>() {
			public xbean.RoleTitle get(xbean.RoleTitle v) { return v.toData(); }
		});
	}

	public static Integer selectActivekey(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleTitle, Integer>() {
				public Integer get(xbean.RoleTitle v) { return v.getActivekey(); }
			});
	}

	public static Integer selectActivetype(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleTitle, Integer>() {
				public Integer get(xbean.RoleTitle v) { return v.getActivetype(); }
			});
	}

	public static java.util.Map<Integer, xbean.GroupTitle> selectTitleinfo(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleTitle, java.util.Map<Integer, xbean.GroupTitle>>() {
				public java.util.Map<Integer, xbean.GroupTitle> get(xbean.RoleTitle v) { return v.getTitleinfoAsData(); }
			});
	}

}
