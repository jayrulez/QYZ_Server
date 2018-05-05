package xtable;

// typed table access point
public class Activationcodesets {
	Activationcodesets() {
	}

	public static xbean.ActivationCodeSet get(Integer key) {
		return _Tables_.getInstance().activationcodesets.get(key);
	}

	public static xbean.ActivationCodeSet get(Integer key, xbean.ActivationCodeSet value) {
		return _Tables_.getInstance().activationcodesets.get(key, value);
	}

	public static void insert(Integer key, xbean.ActivationCodeSet value) {
		_Tables_.getInstance().activationcodesets.insert(key, value);
	}

	public static void delete(Integer key) {
		_Tables_.getInstance().activationcodesets.delete(key);
	}

	public static boolean add(Integer key, xbean.ActivationCodeSet value) {
		return _Tables_.getInstance().activationcodesets.add(key, value);
	}

	public static boolean remove(Integer key) {
		return _Tables_.getInstance().activationcodesets.remove(key);
	}

	public static xdb.TTableCache<Integer, xbean.ActivationCodeSet> getCache() {
		return _Tables_.getInstance().activationcodesets.getCache();
	}

	public static xdb.TTable<Integer, xbean.ActivationCodeSet> getTable() {
		return _Tables_.getInstance().activationcodesets;
	}

	public static xbean.ActivationCodeSet select(Integer key) {
		return getTable().select(key, new xdb.TField<xbean.ActivationCodeSet, xbean.ActivationCodeSet>() {
			public xbean.ActivationCodeSet get(xbean.ActivationCodeSet v) { return v.toData(); }
		});
	}

	public static Integer selectType(Integer key) {
		return getTable().select(key, new xdb.TField<xbean.ActivationCodeSet, Integer>() {
				public Integer get(xbean.ActivationCodeSet v) { return v.getType(); }
			});
	}

	public static java.util.Set<Long> selectValues(Integer key) {
		return getTable().select(key, new xdb.TField<xbean.ActivationCodeSet, java.util.Set<Long>>() {
				public java.util.Set<Long> get(xbean.ActivationCodeSet v) { return v.getValuesAsData(); }
			});
	}

	public static Long selectCreatetime(Integer key) {
		return getTable().select(key, new xdb.TField<xbean.ActivationCodeSet, Long>() {
				public Long get(xbean.ActivationCodeSet v) { return v.getCreatetime(); }
			});
	}

	public static Long selectOpentime(Integer key) {
		return getTable().select(key, new xdb.TField<xbean.ActivationCodeSet, Long>() {
				public Long get(xbean.ActivationCodeSet v) { return v.getOpentime(); }
			});
	}

	public static Long selectExpiratetime(Integer key) {
		return getTable().select(key, new xdb.TField<xbean.ActivationCodeSet, Long>() {
				public Long get(xbean.ActivationCodeSet v) { return v.getExpiratetime(); }
			});
	}

	public static java.util.Set<Integer> selectPlatformset(Integer key) {
		return getTable().select(key, new xdb.TField<xbean.ActivationCodeSet, java.util.Set<Integer>>() {
				public java.util.Set<Integer> get(xbean.ActivationCodeSet v) { return v.getPlatformsetAsData(); }
			});
	}

	public static Integer selectIsshared(Integer key) {
		return getTable().select(key, new xdb.TField<xbean.ActivationCodeSet, Integer>() {
				public Integer get(xbean.ActivationCodeSet v) { return v.getIsshared(); }
			});
	}

	public static Boolean selectIslogin(Integer key) {
		return getTable().select(key, new xdb.TField<xbean.ActivationCodeSet, Boolean>() {
				public Boolean get(xbean.ActivationCodeSet v) { return v.getIslogin(); }
			});
	}

}
