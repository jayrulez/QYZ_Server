package xtable;

// typed table access point
public class Amulet {
	Amulet() {
	}

	public static xbean.RoleAmuletInfo get(Long key) {
		return _Tables_.getInstance().amulet.get(key);
	}

	public static xbean.RoleAmuletInfo get(Long key, xbean.RoleAmuletInfo value) {
		return _Tables_.getInstance().amulet.get(key, value);
	}

	public static void insert(Long key, xbean.RoleAmuletInfo value) {
		_Tables_.getInstance().amulet.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().amulet.delete(key);
	}

	public static boolean add(Long key, xbean.RoleAmuletInfo value) {
		return _Tables_.getInstance().amulet.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().amulet.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.RoleAmuletInfo> getCache() {
		return _Tables_.getInstance().amulet.getCache();
	}

	public static xdb.TTable<Long, xbean.RoleAmuletInfo> getTable() {
		return _Tables_.getInstance().amulet;
	}

	public static xbean.RoleAmuletInfo select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleAmuletInfo, xbean.RoleAmuletInfo>() {
			public xbean.RoleAmuletInfo get(xbean.RoleAmuletInfo v) { return v.toData(); }
		});
	}

	public static java.util.Map<Integer, xbean.AmuletPage> selectPagemap(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleAmuletInfo, java.util.Map<Integer, xbean.AmuletPage>>() {
				public java.util.Map<Integer, xbean.AmuletPage> get(xbean.RoleAmuletInfo v) { return v.getPagemapAsData(); }
			});
	}

}
