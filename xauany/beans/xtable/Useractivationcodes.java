package xtable;

// typed table access point
public class Useractivationcodes {
	Useractivationcodes() {
	}

	public static xbean.UserActivationCode get(Long key) {
		return _Tables_.getInstance().useractivationcodes.get(key);
	}

	public static xbean.UserActivationCode get(Long key, xbean.UserActivationCode value) {
		return _Tables_.getInstance().useractivationcodes.get(key, value);
	}

	public static void insert(Long key, xbean.UserActivationCode value) {
		_Tables_.getInstance().useractivationcodes.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().useractivationcodes.delete(key);
	}

	public static boolean add(Long key, xbean.UserActivationCode value) {
		return _Tables_.getInstance().useractivationcodes.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().useractivationcodes.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.UserActivationCode> getCache() {
		return _Tables_.getInstance().useractivationcodes.getCache();
	}

	public static xdb.TTable<Long, xbean.UserActivationCode> getTable() {
		return _Tables_.getInstance().useractivationcodes;
	}

	public static xbean.UserActivationCode select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.UserActivationCode, xbean.UserActivationCode>() {
			public xbean.UserActivationCode get(xbean.UserActivationCode v) { return v.toData(); }
		});
	}

	public static java.util.Map<Integer, Long> selectAll(Long key) {
		return getTable().select(key, new xdb.TField<xbean.UserActivationCode, java.util.Map<Integer, Long>>() {
				public java.util.Map<Integer, Long> get(xbean.UserActivationCode v) { return v.getAllAsData(); }
			});
	}

}
