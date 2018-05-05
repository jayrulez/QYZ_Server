package xtable;

// typed table access point
public class Roledailyresetdata {
	Roledailyresetdata() {
	}

	public static xbean.DailyResetData get(Long key) {
		return _Tables_.getInstance().roledailyresetdata.get(key);
	}

	public static xbean.DailyResetData get(Long key, xbean.DailyResetData value) {
		return _Tables_.getInstance().roledailyresetdata.get(key, value);
	}

	public static void insert(Long key, xbean.DailyResetData value) {
		_Tables_.getInstance().roledailyresetdata.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().roledailyresetdata.delete(key);
	}

	public static boolean add(Long key, xbean.DailyResetData value) {
		return _Tables_.getInstance().roledailyresetdata.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().roledailyresetdata.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.DailyResetData> getCache() {
		return _Tables_.getInstance().roledailyresetdata.getCache();
	}

	public static xdb.TTable<Long, xbean.DailyResetData> getTable() {
		return _Tables_.getInstance().roledailyresetdata;
	}

	public static xbean.DailyResetData select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.DailyResetData, xbean.DailyResetData>() {
			public xbean.DailyResetData get(xbean.DailyResetData v) { return v.toData(); }
		});
	}

	public static Long selectLastupdatedailytime(Long key) {
		return getTable().select(key, new xdb.TField<xbean.DailyResetData, Long>() {
				public Long get(xbean.DailyResetData v) { return v.getLastupdatedailytime(); }
			});
	}

	public static xbean.DailyArena selectArena(Long key) {
		return getTable().select(key, new xdb.TField<xbean.DailyResetData, xbean.DailyArena>() {
				public xbean.DailyArena get(xbean.DailyResetData v) { return v.getArena(); }
			});
	}

	public static xbean.DailyMonsterExp selectMonsterexp(Long key) {
		return getTable().select(key, new xdb.TField<xbean.DailyResetData, xbean.DailyMonsterExp>() {
				public xbean.DailyMonsterExp get(xbean.DailyResetData v) { return v.getMonsterexp(); }
			});
	}

	public static Integer selectKillexpmonsters(Long key) {
		return getTable().select(key, new xdb.TField<xbean.DailyResetData, Integer>() {
				public Integer get(xbean.DailyResetData v) { return v.getKillexpmonsters(); }
			});
	}

	public static java.util.List<Integer> selectRecexpmonbonus(Long key) {
		return getTable().select(key, new xdb.TField<xbean.DailyResetData, java.util.List<Integer>>() {
				public java.util.List<Integer> get(xbean.DailyResetData v) { return v.getRecexpmonbonusAsData(); }
			});
	}

}
