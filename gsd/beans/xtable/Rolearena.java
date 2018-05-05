package xtable;

// typed table access point
public class Rolearena {
	Rolearena() {
	}

	public static xbean.RoleArena get(Long key) {
		return _Tables_.getInstance().rolearena.get(key);
	}

	public static xbean.RoleArena get(Long key, xbean.RoleArena value) {
		return _Tables_.getInstance().rolearena.get(key, value);
	}

	public static void insert(Long key, xbean.RoleArena value) {
		_Tables_.getInstance().rolearena.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().rolearena.delete(key);
	}

	public static boolean add(Long key, xbean.RoleArena value) {
		return _Tables_.getInstance().rolearena.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().rolearena.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.RoleArena> getCache() {
		return _Tables_.getInstance().rolearena.getCache();
	}

	public static xdb.TTable<Long, xbean.RoleArena> getTable() {
		return _Tables_.getInstance().rolearena;
	}

	public static xbean.RoleArena select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleArena, xbean.RoleArena>() {
			public xbean.RoleArena get(xbean.RoleArena v) { return v.toData(); }
		});
	}

	public static Boolean selectOpen(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleArena, Boolean>() {
				public Boolean get(xbean.RoleArena v) { return v.getOpen(); }
			});
	}

	public static Boolean selectIsrobot(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleArena, Boolean>() {
				public Boolean get(xbean.RoleArena v) { return v.getIsrobot(); }
			});
	}

	public static Long selectLastupdateshengwangtime(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleArena, Long>() {
				public Long get(xbean.RoleArena v) { return v.getLastupdateshengwangtime(); }
			});
	}

	public static java.util.List<Integer> selectChallengeranks(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleArena, java.util.List<Integer>>() {
				public java.util.List<Integer> get(xbean.RoleArena v) { return v.getChallengeranksAsData(); }
			});
	}

	public static java.util.List<Long> selectChallengerobots(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleArena, java.util.List<Long>>() {
				public java.util.List<Long> get(xbean.RoleArena v) { return v.getChallengerobotsAsData(); }
			});
	}

	public static Integer selectBechallenging(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleArena, Integer>() {
				public Integer get(xbean.RoleArena v) { return v.getBechallenging(); }
			});
	}

	public static Long selectBechallengelockexpiretime(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleArena, Long>() {
				public Long get(xbean.RoleArena v) { return v.getBechallengelockexpiretime(); }
			});
	}

	public static java.util.List<xbean.ArenaFightReport> selectFightreports(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleArena, java.util.List<xbean.ArenaFightReport>>() {
				public java.util.List<xbean.ArenaFightReport> get(xbean.RoleArena v) { return v.getFightreportsAsData(); }
			});
	}

	public static Integer selectBestrank(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleArena, Integer>() {
				public Integer get(xbean.RoleArena v) { return v.getBestrank(); }
			});
	}

	public static Boolean selectHaswin(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleArena, Boolean>() {
				public Boolean get(xbean.RoleArena v) { return v.getHaswin(); }
			});
	}

	public static Integer selectFakerank(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleArena, Integer>() {
				public Integer get(xbean.RoleArena v) { return v.getFakerank(); }
			});
	}

}
