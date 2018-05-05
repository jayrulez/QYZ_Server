package xtable;

// typed table access point
public class Uncompletedorderinfos {
	Uncompletedorderinfos() {
	}

	public static xbean.UncompletedOrderInfo get(String key) {
		return _Tables_.getInstance().uncompletedorderinfos.get(key);
	}

	public static xbean.UncompletedOrderInfo get(String key, xbean.UncompletedOrderInfo value) {
		return _Tables_.getInstance().uncompletedorderinfos.get(key, value);
	}

	public static void insert(String key, xbean.UncompletedOrderInfo value) {
		_Tables_.getInstance().uncompletedorderinfos.insert(key, value);
	}

	public static void delete(String key) {
		_Tables_.getInstance().uncompletedorderinfos.delete(key);
	}

	public static boolean add(String key, xbean.UncompletedOrderInfo value) {
		return _Tables_.getInstance().uncompletedorderinfos.add(key, value);
	}

	public static boolean remove(String key) {
		return _Tables_.getInstance().uncompletedorderinfos.remove(key);
	}

	public static xdb.TTableCache<String, xbean.UncompletedOrderInfo> getCache() {
		return _Tables_.getInstance().uncompletedorderinfos.getCache();
	}

	public static xdb.TTable<String, xbean.UncompletedOrderInfo> getTable() {
		return _Tables_.getInstance().uncompletedorderinfos;
	}

	public static xbean.UncompletedOrderInfo select(String key) {
		return getTable().select(key, new xdb.TField<xbean.UncompletedOrderInfo, xbean.UncompletedOrderInfo>() {
			public xbean.UncompletedOrderInfo get(xbean.UncompletedOrderInfo v) { return v.toData(); }
		});
	}

	public static Integer selectServerid(String key) {
		return getTable().select(key, new xdb.TField<xbean.UncompletedOrderInfo, Integer>() {
				public Integer get(xbean.UncompletedOrderInfo v) { return v.getServerid(); }
			});
	}

	public static Integer selectPlattype(String key) {
		return getTable().select(key, new xdb.TField<xbean.UncompletedOrderInfo, Integer>() {
				public Integer get(xbean.UncompletedOrderInfo v) { return v.getPlattype(); }
			});
	}

	public static String selectPlatorderid(String key) {
		return getTable().select(key, new xdb.TField<xbean.UncompletedOrderInfo, String>() {
				public String get(xbean.UncompletedOrderInfo v) { return v.getPlatorderid(); }
			});
	}

	public static Long selectUserid(String key) {
		return getTable().select(key, new xdb.TField<xbean.UncompletedOrderInfo, Long>() {
				public Long get(xbean.UncompletedOrderInfo v) { return v.getUserid(); }
			});
	}

	public static byte [] selectVars(String key) {
		return getTable().select(key, new xdb.TField<xbean.UncompletedOrderInfo, byte []>() {
				public byte [] get(xbean.UncompletedOrderInfo v) { return v.getVarsCopy(); }
			});
	}

	public static Integer selectTimes(String key) {
		return getTable().select(key, new xdb.TField<xbean.UncompletedOrderInfo, Integer>() {
				public Integer get(xbean.UncompletedOrderInfo v) { return v.getTimes(); }
			});
	}

}
