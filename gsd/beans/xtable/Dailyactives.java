package xtable;

// typed table access point
public class Dailyactives {
	Dailyactives() {
	}

	public static xbean.DailyActive get(Long key) {
		return _Tables_.getInstance().dailyactives.get(key);
	}

	public static xbean.DailyActive get(Long key, xbean.DailyActive value) {
		return _Tables_.getInstance().dailyactives.get(key, value);
	}

	public static void insert(Long key, xbean.DailyActive value) {
		_Tables_.getInstance().dailyactives.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().dailyactives.delete(key);
	}

	public static boolean add(Long key, xbean.DailyActive value) {
		return _Tables_.getInstance().dailyactives.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().dailyactives.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.DailyActive> getCache() {
		return _Tables_.getInstance().dailyactives.getCache();
	}

	public static xdb.TTable<Long, xbean.DailyActive> getTable() {
		return _Tables_.getInstance().dailyactives;
	}

	public static xbean.DailyActive select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.DailyActive, xbean.DailyActive>() {
			public xbean.DailyActive get(xbean.DailyActive v) { return v.toData(); }
		});
	}

	public static java.util.Map<Integer, Integer> selectActivetimes(Long key) {
		return getTable().select(key, new xdb.TField<xbean.DailyActive, java.util.Map<Integer, Integer>>() {
				public java.util.Map<Integer, Integer> get(xbean.DailyActive v) { return v.getActivetimesAsData(); }
			});
	}

	public static java.util.List<Integer> selectReceivedbonus(Long key) {
		return getTable().select(key, new xdb.TField<xbean.DailyActive, java.util.List<Integer>>() {
				public java.util.List<Integer> get(xbean.DailyActive v) { return v.getReceivedbonusAsData(); }
			});
	}

	public static Integer selectActivescores(Long key) {
		return getTable().select(key, new xdb.TField<xbean.DailyActive, Integer>() {
				public Integer get(xbean.DailyActive v) { return v.getActivescores(); }
			});
	}

	public static java.util.Map<Integer, Integer> selectUndoactive(Long key) {
		return getTable().select(key, new xdb.TField<xbean.DailyActive, java.util.Map<Integer, Integer>>() {
				public java.util.Map<Integer, Integer> get(xbean.DailyActive v) { return v.getUndoactiveAsData(); }
			});
	}

	public static java.util.Map<Integer, Integer> selectEventdonetimes(Long key) {
		return getTable().select(key, new xdb.TField<xbean.DailyActive, java.util.Map<Integer, Integer>>() {
				public java.util.Map<Integer, Integer> get(xbean.DailyActive v) { return v.getEventdonetimesAsData(); }
			});
	}

	public static Integer selectLastactivelvl(Long key) {
		return getTable().select(key, new xdb.TField<xbean.DailyActive, Integer>() {
				public Integer get(xbean.DailyActive v) { return v.getLastactivelvl(); }
			});
	}

}
