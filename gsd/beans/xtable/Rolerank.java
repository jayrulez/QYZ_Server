package xtable;

// typed table access point
public class Rolerank {
	Rolerank() {
	}

	public static xbean.RoleRank get(Long key) {
		return _Tables_.getInstance().rolerank.get(key);
	}

	public static xbean.RoleRank get(Long key, xbean.RoleRank value) {
		return _Tables_.getInstance().rolerank.get(key, value);
	}

	public static void insert(Long key, xbean.RoleRank value) {
		_Tables_.getInstance().rolerank.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().rolerank.delete(key);
	}

	public static boolean add(Long key, xbean.RoleRank value) {
		return _Tables_.getInstance().rolerank.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().rolerank.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.RoleRank> getCache() {
		return _Tables_.getInstance().rolerank.getCache();
	}

	public static xdb.TTable<Long, xbean.RoleRank> getTable() {
		return _Tables_.getInstance().rolerank;
	}

	public static xbean.RoleRank select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleRank, xbean.RoleRank>() {
			public xbean.RoleRank get(xbean.RoleRank v) { return v.toData(); }
		});
	}

	public static java.util.Map<Integer, xbean.ARankInfo> selectRanks(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleRank, java.util.Map<Integer, xbean.ARankInfo>>() {
				public java.util.Map<Integer, xbean.ARankInfo> get(xbean.RoleRank v) { return v.getRanksAsData(); }
			});
	}

}
