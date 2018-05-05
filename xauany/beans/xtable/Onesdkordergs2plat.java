package xtable;

// typed table access point
public class Onesdkordergs2plat {
	Onesdkordergs2plat() {
	}

	public static String get(String key) {
		return _Tables_.getInstance().onesdkordergs2plat.get(key);
	}

	public static String get(String key, String value) {
		return _Tables_.getInstance().onesdkordergs2plat.get(key, value);
	}

	public static void insert(String key, String value) {
		_Tables_.getInstance().onesdkordergs2plat.insert(key, value);
	}

	public static void delete(String key) {
		_Tables_.getInstance().onesdkordergs2plat.delete(key);
	}

	public static boolean add(String key, String value) {
		return _Tables_.getInstance().onesdkordergs2plat.add(key, value);
	}

	public static boolean remove(String key) {
		return _Tables_.getInstance().onesdkordergs2plat.remove(key);
	}

	public static xdb.TTableCache<String, String> getCache() {
		return _Tables_.getInstance().onesdkordergs2plat.getCache();
	}

	public static xdb.TTable<String, String> getTable() {
		return _Tables_.getInstance().onesdkordergs2plat;
	}

	public static String select(String key) {
		return getTable().select(key, new xdb.TField<String, String>() {
			public String get(String v) { return v; }
		});
	}

}
