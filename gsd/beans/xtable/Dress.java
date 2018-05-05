package xtable;

// typed table access point
public class Dress {
	Dress() {
	}

	public static xbean.RoleDress get(Long key) {
		return _Tables_.getInstance().dress.get(key);
	}

	public static xbean.RoleDress get(Long key, xbean.RoleDress value) {
		return _Tables_.getInstance().dress.get(key, value);
	}

	public static void insert(Long key, xbean.RoleDress value) {
		_Tables_.getInstance().dress.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().dress.delete(key);
	}

	public static boolean add(Long key, xbean.RoleDress value) {
		return _Tables_.getInstance().dress.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().dress.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.RoleDress> getCache() {
		return _Tables_.getInstance().dress.getCache();
	}

	public static xdb.TTable<Long, xbean.RoleDress> getTable() {
		return _Tables_.getInstance().dress;
	}

	public static xbean.RoleDress select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleDress, xbean.RoleDress>() {
			public xbean.RoleDress get(xbean.RoleDress v) { return v.toData(); }
		});
	}

	public static java.util.Map<Integer, xbean.Dress> selectDresses(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleDress, java.util.Map<Integer, xbean.Dress>>() {
				public java.util.Map<Integer, xbean.Dress> get(xbean.RoleDress v) { return v.getDressesAsData(); }
			});
	}

	public static Integer selectActivedress(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleDress, Integer>() {
				public Integer get(xbean.RoleDress v) { return v.getActivedress(); }
			});
	}

}
