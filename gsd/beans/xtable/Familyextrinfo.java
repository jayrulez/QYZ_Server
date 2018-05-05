package xtable;

// typed table access point
public class Familyextrinfo {
	Familyextrinfo() {
	}

	public static xbean.IdList get(Long key) {
		return _Tables_.getInstance().familyextrinfo.get(key);
	}

	public static xbean.IdList get(Long key, xbean.IdList value) {
		return _Tables_.getInstance().familyextrinfo.get(key, value);
	}

	public static void insert(Long key, xbean.IdList value) {
		_Tables_.getInstance().familyextrinfo.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().familyextrinfo.delete(key);
	}

	public static boolean add(Long key, xbean.IdList value) {
		return _Tables_.getInstance().familyextrinfo.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().familyextrinfo.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.IdList> getCache() {
		return _Tables_.getInstance().familyextrinfo.getCache();
	}

	public static xdb.TTable<Long, xbean.IdList> getTable() {
		return _Tables_.getInstance().familyextrinfo;
	}

	public static xbean.IdList select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.IdList, xbean.IdList>() {
			public xbean.IdList get(xbean.IdList v) { return v.toData(); }
		});
	}

	public static java.util.List<Long> selectLists(Long key) {
		return getTable().select(key, new xdb.TField<xbean.IdList, java.util.List<Long>>() {
				public java.util.List<Long> get(xbean.IdList v) { return v.getListsAsData(); }
			});
	}

}
