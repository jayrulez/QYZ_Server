package xtable;

// typed table access point
public class Activationcodes {
	Activationcodes() {
	}

	public static xbean.ActivationCode get(Long key) {
		return _Tables_.getInstance().activationcodes.get(key);
	}

	public static xbean.ActivationCode get(Long key, xbean.ActivationCode value) {
		return _Tables_.getInstance().activationcodes.get(key, value);
	}

	public static void insert(Long key, xbean.ActivationCode value) {
		_Tables_.getInstance().activationcodes.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().activationcodes.delete(key);
	}

	public static boolean add(Long key, xbean.ActivationCode value) {
		return _Tables_.getInstance().activationcodes.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().activationcodes.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.ActivationCode> getCache() {
		return _Tables_.getInstance().activationcodes.getCache();
	}

	public static xdb.TTable<Long, xbean.ActivationCode> getTable() {
		return _Tables_.getInstance().activationcodes;
	}

	public static xbean.ActivationCode select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.ActivationCode, xbean.ActivationCode>() {
			public xbean.ActivationCode get(xbean.ActivationCode v) { return v.toData(); }
		});
	}

	public static Integer selectType(Long key) {
		return getTable().select(key, new xdb.TField<xbean.ActivationCode, Integer>() {
				public Integer get(xbean.ActivationCode v) { return v.getType(); }
			});
	}

	public static Integer selectStatus(Long key) {
		return getTable().select(key, new xdb.TField<xbean.ActivationCode, Integer>() {
				public Integer get(xbean.ActivationCode v) { return v.getStatus(); }
			});
	}

	public static Long selectUsetime(Long key) {
		return getTable().select(key, new xdb.TField<xbean.ActivationCode, Long>() {
				public Long get(xbean.ActivationCode v) { return v.getUsetime(); }
			});
	}

}
