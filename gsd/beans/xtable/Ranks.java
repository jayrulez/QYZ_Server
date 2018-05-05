package xtable;

// typed table access point
public class Ranks {
	Ranks() {
	}

	public static xbean.ARank get(Integer key) {
		return _Tables_.getInstance().ranks.get(key);
	}

	public static xbean.ARank get(Integer key, xbean.ARank value) {
		return _Tables_.getInstance().ranks.get(key, value);
	}

	public static void insert(Integer key, xbean.ARank value) {
		_Tables_.getInstance().ranks.insert(key, value);
	}

	public static void delete(Integer key) {
		_Tables_.getInstance().ranks.delete(key);
	}

	public static boolean add(Integer key, xbean.ARank value) {
		return _Tables_.getInstance().ranks.add(key, value);
	}

	public static boolean remove(Integer key) {
		return _Tables_.getInstance().ranks.remove(key);
	}

	public static xdb.TTableCache<Integer, xbean.ARank> getCache() {
		return _Tables_.getInstance().ranks.getCache();
	}

	public static xdb.TTable<Integer, xbean.ARank> getTable() {
		return _Tables_.getInstance().ranks;
	}

	public static xbean.ARank select(Integer key) {
		return getTable().select(key, new xdb.TField<xbean.ARank, xbean.ARank>() {
			public xbean.ARank get(xbean.ARank v) { return v.toData(); }
		});
	}

	public static java.util.Map<Long, Integer> selectId2rank(Integer key) {
		return getTable().select(key, new xdb.TField<xbean.ARank, java.util.Map<Long, Integer>>() {
				public java.util.Map<Long, Integer> get(xbean.ARank v) { return v.getId2rankAsData(); }
			});
	}

	public static java.util.Map<Integer, xbean.RankRecord> selectRecords(Integer key) {
		return getTable().select(key, new xdb.TField<xbean.ARank, java.util.Map<Integer, xbean.RankRecord>>() {
				public java.util.Map<Integer, xbean.RankRecord> get(xbean.ARank v) { return v.getRecordsAsData(); }
			});
	}

	public static Long selectCreatetime(Integer key) {
		return getTable().select(key, new xdb.TField<xbean.ARank, Long>() {
				public Long get(xbean.ARank v) { return v.getCreatetime(); }
			});
	}

}
