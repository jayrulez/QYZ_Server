package xtable;

// typed table access point
public class Rolemaps {
	Rolemaps() {
	}

	public static xbean.RoleMap get(Long key) {
		return _Tables_.getInstance().rolemaps.get(key);
	}

	public static xbean.RoleMap get(Long key, xbean.RoleMap value) {
		return _Tables_.getInstance().rolemaps.get(key, value);
	}

	public static void insert(Long key, xbean.RoleMap value) {
		_Tables_.getInstance().rolemaps.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().rolemaps.delete(key);
	}

	public static boolean add(Long key, xbean.RoleMap value) {
		return _Tables_.getInstance().rolemaps.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().rolemaps.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.RoleMap> getCache() {
		return _Tables_.getInstance().rolemaps.getCache();
	}

	public static xdb.TTable<Long, xbean.RoleMap> getTable() {
		return _Tables_.getInstance().rolemaps;
	}

	public static xbean.RoleMap select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleMap, xbean.RoleMap>() {
			public xbean.RoleMap get(xbean.RoleMap v) { return v.toData(); }
		});
	}

	public static java.util.Map<Integer, Integer> selectPkstates(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleMap, java.util.Map<Integer, Integer>>() {
				public java.util.Map<Integer, Integer> get(xbean.RoleMap v) { return v.getPkstatesAsData(); }
			});
	}

	public static Boolean selectFinishprologue(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleMap, Boolean>() {
				public Boolean get(xbean.RoleMap v) { return v.getFinishprologue(); }
			});
	}

	public static Integer selectHp(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleMap, Integer>() {
				public Integer get(xbean.RoleMap v) { return v.getHp(); }
			});
	}

	public static Integer selectMp(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleMap, Integer>() {
				public Integer get(xbean.RoleMap v) { return v.getMp(); }
			});
	}

	public static java.util.List<xbean.RoleMapInfo> selectPrevs(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleMap, java.util.List<xbean.RoleMapInfo>>() {
				public java.util.List<xbean.RoleMapInfo> get(xbean.RoleMap v) { return v.getPrevsAsData(); }
			});
	}

	public static Boolean selectIsnew(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleMap, Boolean>() {
				public Boolean get(xbean.RoleMap v) { return v.getIsnew(); }
			});
	}

}
