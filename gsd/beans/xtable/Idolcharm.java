package xtable;

// typed table access point
public class Idolcharm {
	Idolcharm() {
	}

	public static xbean.IdolCharmInfo get(Long key) {
		return _Tables_.getInstance().idolcharm.get(key);
	}

	public static xbean.IdolCharmInfo get(Long key, xbean.IdolCharmInfo value) {
		return _Tables_.getInstance().idolcharm.get(key, value);
	}

	public static void insert(Long key, xbean.IdolCharmInfo value) {
		_Tables_.getInstance().idolcharm.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().idolcharm.delete(key);
	}

	public static boolean add(Long key, xbean.IdolCharmInfo value) {
		return _Tables_.getInstance().idolcharm.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().idolcharm.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.IdolCharmInfo> getCache() {
		return _Tables_.getInstance().idolcharm.getCache();
	}

	public static xdb.TTable<Long, xbean.IdolCharmInfo> getTable() {
		return _Tables_.getInstance().idolcharm;
	}

	public static xbean.IdolCharmInfo select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.IdolCharmInfo, xbean.IdolCharmInfo>() {
			public xbean.IdolCharmInfo get(xbean.IdolCharmInfo v) { return v.toData(); }
		});
	}

	public static Integer selectCharm(Long key) {
		return getTable().select(key, new xdb.TField<xbean.IdolCharmInfo, Integer>() {
				public Integer get(xbean.IdolCharmInfo v) { return v.getCharm(); }
			});
	}

	public static Long selectGuardid(Long key) {
		return getTable().select(key, new xdb.TField<xbean.IdolCharmInfo, Long>() {
				public Long get(xbean.IdolCharmInfo v) { return v.getGuardid(); }
			});
	}

	public static Integer selectGuarddegree(Long key) {
		return getTable().select(key, new xdb.TField<xbean.IdolCharmInfo, Integer>() {
				public Integer get(xbean.IdolCharmInfo v) { return v.getGuarddegree(); }
			});
	}

	public static Long selectGuardtime(Long key) {
		return getTable().select(key, new xdb.TField<xbean.IdolCharmInfo, Long>() {
				public Long get(xbean.IdolCharmInfo v) { return v.getGuardtime(); }
			});
	}

}
