package xtable;

// typed table access point
public class Testorderinfos {
	Testorderinfos() {
	}

	public static xbean.TestOrderInfo get(String key) {
		return _Tables_.getInstance().testorderinfos.get(key);
	}

	public static xbean.TestOrderInfo get(String key, xbean.TestOrderInfo value) {
		return _Tables_.getInstance().testorderinfos.get(key, value);
	}

	public static void insert(String key, xbean.TestOrderInfo value) {
		_Tables_.getInstance().testorderinfos.insert(key, value);
	}

	public static void delete(String key) {
		_Tables_.getInstance().testorderinfos.delete(key);
	}

	public static boolean add(String key, xbean.TestOrderInfo value) {
		return _Tables_.getInstance().testorderinfos.add(key, value);
	}

	public static boolean remove(String key) {
		return _Tables_.getInstance().testorderinfos.remove(key);
	}

	public static xdb.TTableCache<String, xbean.TestOrderInfo> getCache() {
		return _Tables_.getInstance().testorderinfos.getCache();
	}

	public static xdb.TTable<String, xbean.TestOrderInfo> getTable() {
		return _Tables_.getInstance().testorderinfos;
	}

	public static xbean.TestOrderInfo select(String key) {
		return getTable().select(key, new xdb.TField<xbean.TestOrderInfo, xbean.TestOrderInfo>() {
			public xbean.TestOrderInfo get(xbean.TestOrderInfo v) { return v.toData(); }
		});
	}

	public static Long selectCreatetime(String key) {
		return getTable().select(key, new xdb.TField<xbean.TestOrderInfo, Long>() {
				public Long get(xbean.TestOrderInfo v) { return v.getCreatetime(); }
			});
	}

	public static String selectGsorderid(String key) {
		return getTable().select(key, new xdb.TField<xbean.TestOrderInfo, String>() {
				public String get(xbean.TestOrderInfo v) { return v.getGsorderid(); }
			});
	}

	public static String selectUseridentity(String key) {
		return getTable().select(key, new xdb.TField<xbean.TestOrderInfo, String>() {
				public String get(xbean.TestOrderInfo v) { return v.getUseridentity(); }
			});
	}

	public static byte [] selectVars(String key) {
		return getTable().select(key, new xdb.TField<xbean.TestOrderInfo, byte []>() {
				public byte [] get(xbean.TestOrderInfo v) { return v.getVarsCopy(); }
			});
	}

}
