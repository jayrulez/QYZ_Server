package xtable;

// typed table access point
public class Roleconfigure {
	Roleconfigure() {
	}

	public static xbean.RoleConfigure get(Long key) {
		return _Tables_.getInstance().roleconfigure.get(key);
	}

	public static xbean.RoleConfigure get(Long key, xbean.RoleConfigure value) {
		return _Tables_.getInstance().roleconfigure.get(key, value);
	}

	public static void insert(Long key, xbean.RoleConfigure value) {
		_Tables_.getInstance().roleconfigure.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().roleconfigure.delete(key);
	}

	public static boolean add(Long key, xbean.RoleConfigure value) {
		return _Tables_.getInstance().roleconfigure.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().roleconfigure.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.RoleConfigure> getCache() {
		return _Tables_.getInstance().roleconfigure.getCache();
	}

	public static xdb.TTable<Long, xbean.RoleConfigure> getTable() {
		return _Tables_.getInstance().roleconfigure;
	}

	public static xbean.RoleConfigure select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleConfigure, xbean.RoleConfigure>() {
			public xbean.RoleConfigure get(xbean.RoleConfigure v) { return v.toData(); }
		});
	}

	public static java.util.Map<String, String> selectDatas(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleConfigure, java.util.Map<String, String>>() {
				public java.util.Map<String, String> get(xbean.RoleConfigure v) { return v.getDatasAsData(); }
			});
	}

}
