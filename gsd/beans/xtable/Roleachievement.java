package xtable;

// typed table access point
public class Roleachievement {
	Roleachievement() {
	}

	public static xbean.RoleAchievement get(Long key) {
		return _Tables_.getInstance().roleachievement.get(key);
	}

	public static xbean.RoleAchievement get(Long key, xbean.RoleAchievement value) {
		return _Tables_.getInstance().roleachievement.get(key, value);
	}

	public static void insert(Long key, xbean.RoleAchievement value) {
		_Tables_.getInstance().roleachievement.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().roleachievement.delete(key);
	}

	public static boolean add(Long key, xbean.RoleAchievement value) {
		return _Tables_.getInstance().roleachievement.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().roleachievement.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.RoleAchievement> getCache() {
		return _Tables_.getInstance().roleachievement.getCache();
	}

	public static xdb.TTable<Long, xbean.RoleAchievement> getTable() {
		return _Tables_.getInstance().roleachievement;
	}

	public static xbean.RoleAchievement select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleAchievement, xbean.RoleAchievement>() {
			public xbean.RoleAchievement get(xbean.RoleAchievement v) { return v.toData(); }
		});
	}

	public static java.util.Map<Integer, Integer> selectAchievementstates(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleAchievement, java.util.Map<Integer, Integer>>() {
				public java.util.Map<Integer, Integer> get(xbean.RoleAchievement v) { return v.getAchievementstatesAsData(); }
			});
	}

	public static java.util.Map<Integer, Long> selectCounters(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleAchievement, java.util.Map<Integer, Long>>() {
				public java.util.Map<Integer, Long> get(xbean.RoleAchievement v) { return v.getCountersAsData(); }
			});
	}

	public static java.util.Map<Integer, xbean.CounterSet> selectCountersets(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleAchievement, java.util.Map<Integer, xbean.CounterSet>>() {
				public java.util.Map<Integer, xbean.CounterSet> get(xbean.RoleAchievement v) { return v.getCountersetsAsData(); }
			});
	}

}
