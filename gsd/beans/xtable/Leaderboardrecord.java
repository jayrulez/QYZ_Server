package xtable;

// typed table access point
public class Leaderboardrecord {
	Leaderboardrecord() {
	}

	public static xbean.BoardRecord get(Long key) {
		return _Tables_.getInstance().leaderboardrecord.get(key);
	}

	public static xbean.BoardRecord get(Long key, xbean.BoardRecord value) {
		return _Tables_.getInstance().leaderboardrecord.get(key, value);
	}

	public static void insert(Long key, xbean.BoardRecord value) {
		_Tables_.getInstance().leaderboardrecord.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().leaderboardrecord.delete(key);
	}

	public static boolean add(Long key, xbean.BoardRecord value) {
		return _Tables_.getInstance().leaderboardrecord.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().leaderboardrecord.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.BoardRecord> getCache() {
		return _Tables_.getInstance().leaderboardrecord.getCache();
	}

	public static xdb.TTable<Long, xbean.BoardRecord> getTable() {
		return _Tables_.getInstance().leaderboardrecord;
	}

	public static xbean.BoardRecord select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.BoardRecord, xbean.BoardRecord>() {
			public xbean.BoardRecord get(xbean.BoardRecord v) { return v.toData(); }
		});
	}

	public static java.util.Map<Integer, xbean.BoardRecordEntry> selectRecords(Long key) {
		return getTable().select(key, new xdb.TField<xbean.BoardRecord, java.util.Map<Integer, xbean.BoardRecordEntry>>() {
				public java.util.Map<Integer, xbean.BoardRecordEntry> get(xbean.BoardRecord v) { return v.getRecordsAsData(); }
			});
	}

}
