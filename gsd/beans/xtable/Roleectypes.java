package xtable;

// typed table access point
public class Roleectypes {
	Roleectypes() {
	}

	public static xbean.RoleEctype get(Long key) {
		return _Tables_.getInstance().roleectypes.get(key);
	}

	public static xbean.RoleEctype get(Long key, xbean.RoleEctype value) {
		return _Tables_.getInstance().roleectypes.get(key, value);
	}

	public static void insert(Long key, xbean.RoleEctype value) {
		_Tables_.getInstance().roleectypes.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().roleectypes.delete(key);
	}

	public static boolean add(Long key, xbean.RoleEctype value) {
		return _Tables_.getInstance().roleectypes.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().roleectypes.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.RoleEctype> getCache() {
		return _Tables_.getInstance().roleectypes.getCache();
	}

	public static xdb.TTable<Long, xbean.RoleEctype> getTable() {
		return _Tables_.getInstance().roleectypes;
	}

	public static xbean.RoleEctype select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleEctype, xbean.RoleEctype>() {
			public xbean.RoleEctype get(xbean.RoleEctype v) { return v.toData(); }
		});
	}

	public static java.util.Map<Integer, xbean.ClimbTowerInfo> selectClimbtowers(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleEctype, java.util.Map<Integer, xbean.ClimbTowerInfo>>() {
				public java.util.Map<Integer, xbean.ClimbTowerInfo> get(xbean.RoleEctype v) { return v.getClimbtowersAsData(); }
			});
	}

	public static java.util.Map<Integer, xbean.ChapterInfo> selectChapters(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleEctype, java.util.Map<Integer, xbean.ChapterInfo>>() {
				public java.util.Map<Integer, xbean.ChapterInfo> get(xbean.RoleEctype v) { return v.getChaptersAsData(); }
			});
	}

	public static java.util.Map<Integer, xbean.DailyInfo> selectDailys(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleEctype, java.util.Map<Integer, xbean.DailyInfo>>() {
				public java.util.Map<Integer, xbean.DailyInfo> get(xbean.RoleEctype v) { return v.getDailysAsData(); }
			});
	}

	public static java.util.Map<Integer, Integer> selectMultistory(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleEctype, java.util.Map<Integer, Integer>>() {
				public java.util.Map<Integer, Integer> get(xbean.RoleEctype v) { return v.getMultistoryAsData(); }
			});
	}

	public static java.util.Map<Integer, xbean.HeroesGroupInfo> selectHerogroups(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleEctype, java.util.Map<Integer, xbean.HeroesGroupInfo>>() {
				public java.util.Map<Integer, xbean.HeroesGroupInfo> get(xbean.RoleEctype v) { return v.getHerogroupsAsData(); }
			});
	}

	public static xbean.TeamFightInfo selectTeamfight(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleEctype, xbean.TeamFightInfo>() {
				public xbean.TeamFightInfo get(xbean.RoleEctype v) { return v.getTeamfight(); }
			});
	}

	public static Integer selectMatchtype(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleEctype, Integer>() {
				public Integer get(xbean.RoleEctype v) { return v.getMatchtype(); }
			});
	}

	public static Long selectNextmatchtime(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleEctype, Long>() {
				public Long get(xbean.RoleEctype v) { return v.getNextmatchtime(); }
			});
	}

	public static Integer selectMultiectypid(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleEctype, Integer>() {
				public Integer get(xbean.RoleEctype v) { return v.getMultiectypid(); }
			});
	}

}
