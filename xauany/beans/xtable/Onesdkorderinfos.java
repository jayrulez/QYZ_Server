package xtable;

// typed table access point
public class Onesdkorderinfos {
	Onesdkorderinfos() {
	}

	public static xbean.OnesdkOrderInfo get(String key) {
		return _Tables_.getInstance().onesdkorderinfos.get(key);
	}

	public static xbean.OnesdkOrderInfo get(String key, xbean.OnesdkOrderInfo value) {
		return _Tables_.getInstance().onesdkorderinfos.get(key, value);
	}

	public static void insert(String key, xbean.OnesdkOrderInfo value) {
		_Tables_.getInstance().onesdkorderinfos.insert(key, value);
	}

	public static void delete(String key) {
		_Tables_.getInstance().onesdkorderinfos.delete(key);
	}

	public static boolean add(String key, xbean.OnesdkOrderInfo value) {
		return _Tables_.getInstance().onesdkorderinfos.add(key, value);
	}

	public static boolean remove(String key) {
		return _Tables_.getInstance().onesdkorderinfos.remove(key);
	}

	public static xdb.TTableCache<String, xbean.OnesdkOrderInfo> getCache() {
		return _Tables_.getInstance().onesdkorderinfos.getCache();
	}

	public static xdb.TTable<String, xbean.OnesdkOrderInfo> getTable() {
		return _Tables_.getInstance().onesdkorderinfos;
	}

	public static xbean.OnesdkOrderInfo select(String key) {
		return getTable().select(key, new xdb.TField<xbean.OnesdkOrderInfo, xbean.OnesdkOrderInfo>() {
			public xbean.OnesdkOrderInfo get(xbean.OnesdkOrderInfo v) { return v.toData(); }
		});
	}

	public static Long selectCreatetime(String key) {
		return getTable().select(key, new xdb.TField<xbean.OnesdkOrderInfo, Long>() {
				public Long get(xbean.OnesdkOrderInfo v) { return v.getCreatetime(); }
			});
	}

	public static String selectGsorderid(String key) {
		return getTable().select(key, new xdb.TField<xbean.OnesdkOrderInfo, String>() {
				public String get(xbean.OnesdkOrderInfo v) { return v.getGsorderid(); }
			});
	}

	public static String selectUseridentity(String key) {
		return getTable().select(key, new xdb.TField<xbean.OnesdkOrderInfo, String>() {
				public String get(xbean.OnesdkOrderInfo v) { return v.getUseridentity(); }
			});
	}

	public static byte [] selectVars(String key) {
		return getTable().select(key, new xdb.TField<xbean.OnesdkOrderInfo, byte []>() {
				public byte [] get(xbean.OnesdkOrderInfo v) { return v.getVarsCopy(); }
			});
	}

}
