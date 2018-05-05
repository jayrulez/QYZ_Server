package xtable;

// typed table access point
public class Globalactivationcodes {
	Globalactivationcodes() {
	}

	public static xbean.GlobalActivationCode get(Integer key) {
		return _Tables_.getInstance().globalactivationcodes.get(key);
	}

	public static xbean.GlobalActivationCode get(Integer key, xbean.GlobalActivationCode value) {
		return _Tables_.getInstance().globalactivationcodes.get(key, value);
	}

	public static void insert(Integer key, xbean.GlobalActivationCode value) {
		_Tables_.getInstance().globalactivationcodes.insert(key, value);
	}

	public static void delete(Integer key) {
		_Tables_.getInstance().globalactivationcodes.delete(key);
	}

	public static boolean add(Integer key, xbean.GlobalActivationCode value) {
		return _Tables_.getInstance().globalactivationcodes.add(key, value);
	}

	public static boolean remove(Integer key) {
		return _Tables_.getInstance().globalactivationcodes.remove(key);
	}

	public static xdb.TTableCache<Integer, xbean.GlobalActivationCode> getCache() {
		return _Tables_.getInstance().globalactivationcodes.getCache();
	}

	public static xdb.TTable<Integer, xbean.GlobalActivationCode> getTable() {
		return _Tables_.getInstance().globalactivationcodes;
	}

	public static xbean.GlobalActivationCode select(Integer key) {
		return getTable().select(key, new xdb.TField<xbean.GlobalActivationCode, xbean.GlobalActivationCode>() {
			public xbean.GlobalActivationCode get(xbean.GlobalActivationCode v) { return v.toData(); }
		});
	}

	public static java.util.Set<Integer> selectAlltypes(Integer key) {
		return getTable().select(key, new xdb.TField<xbean.GlobalActivationCode, java.util.Set<Integer>>() {
				public java.util.Set<Integer> get(xbean.GlobalActivationCode v) { return v.getAlltypesAsData(); }
			});
	}

}
