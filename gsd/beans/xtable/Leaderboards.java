package xtable;

// typed table access point
public class Leaderboards {
	Leaderboards() {
	}

	public static xbean.BoardInfo get(Integer key) {
		return _Tables_.getInstance().leaderboards.get(key);
	}

	public static xbean.BoardInfo get(Integer key, xbean.BoardInfo value) {
		return _Tables_.getInstance().leaderboards.get(key, value);
	}

	public static void insert(Integer key, xbean.BoardInfo value) {
		_Tables_.getInstance().leaderboards.insert(key, value);
	}

	public static void delete(Integer key) {
		_Tables_.getInstance().leaderboards.delete(key);
	}

	public static boolean add(Integer key, xbean.BoardInfo value) {
		return _Tables_.getInstance().leaderboards.add(key, value);
	}

	public static boolean remove(Integer key) {
		return _Tables_.getInstance().leaderboards.remove(key);
	}

	public static xdb.TTableCache<Integer, xbean.BoardInfo> getCache() {
		return _Tables_.getInstance().leaderboards.getCache();
	}

	public static xdb.TTable<Integer, xbean.BoardInfo> getTable() {
		return _Tables_.getInstance().leaderboards;
	}

	public static xbean.BoardInfo select(Integer key) {
		return getTable().select(key, new xdb.TField<xbean.BoardInfo, xbean.BoardInfo>() {
			public xbean.BoardInfo get(xbean.BoardInfo v) { return v.toData(); }
		});
	}

	public static java.util.Map<Integer, xbean.BoardEntry> selectLatestboard(Integer key) {
		return getTable().select(key, new xdb.TField<xbean.BoardInfo, java.util.Map<Integer, xbean.BoardEntry>>() {
				public java.util.Map<Integer, xbean.BoardEntry> get(xbean.BoardInfo v) { return v.getLatestboardAsData(); }
			});
	}

	public static java.util.Map<Long, Integer> selectRolerank(Integer key) {
		return getTable().select(key, new xdb.TField<xbean.BoardInfo, java.util.Map<Long, Integer>>() {
				public java.util.Map<Long, Integer> get(xbean.BoardInfo v) { return v.getRolerankAsData(); }
			});
	}

	public static Long selectLastupdatetime(Integer key) {
		return getTable().select(key, new xdb.TField<xbean.BoardInfo, Long>() {
				public Long get(xbean.BoardInfo v) { return v.getLastupdatetime(); }
			});
	}

	public static java.util.Map<Long, Integer> selectYesterdayrank(Integer key) {
		return getTable().select(key, new xdb.TField<xbean.BoardInfo, java.util.Map<Long, Integer>>() {
				public java.util.Map<Long, Integer> get(xbean.BoardInfo v) { return v.getYesterdayrankAsData(); }
			});
	}

}
