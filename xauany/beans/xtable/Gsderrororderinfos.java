package xtable;

// typed table access point
public class Gsderrororderinfos {
	Gsderrororderinfos() {
	}

	public static xbean.GsdErrorOrderInfo get(String key) {
		return _Tables_.getInstance().gsderrororderinfos.get(key);
	}

	public static xbean.GsdErrorOrderInfo get(String key, xbean.GsdErrorOrderInfo value) {
		return _Tables_.getInstance().gsderrororderinfos.get(key, value);
	}

	public static void insert(String key, xbean.GsdErrorOrderInfo value) {
		_Tables_.getInstance().gsderrororderinfos.insert(key, value);
	}

	public static void delete(String key) {
		_Tables_.getInstance().gsderrororderinfos.delete(key);
	}

	public static boolean add(String key, xbean.GsdErrorOrderInfo value) {
		return _Tables_.getInstance().gsderrororderinfos.add(key, value);
	}

	public static boolean remove(String key) {
		return _Tables_.getInstance().gsderrororderinfos.remove(key);
	}

	public static xdb.TTableCache<String, xbean.GsdErrorOrderInfo> getCache() {
		return _Tables_.getInstance().gsderrororderinfos.getCache();
	}

	public static xdb.TTable<String, xbean.GsdErrorOrderInfo> getTable() {
		return _Tables_.getInstance().gsderrororderinfos;
	}

	public static xbean.GsdErrorOrderInfo select(String key) {
		return getTable().select(key, new xdb.TField<xbean.GsdErrorOrderInfo, xbean.GsdErrorOrderInfo>() {
			public xbean.GsdErrorOrderInfo get(xbean.GsdErrorOrderInfo v) { return v.toData(); }
		});
	}

	public static xbean.UncompletedOrderInfo selectOrder(String key) {
		return getTable().select(key, new xdb.TField<xbean.GsdErrorOrderInfo, xbean.UncompletedOrderInfo>() {
				public xbean.UncompletedOrderInfo get(xbean.GsdErrorOrderInfo v) { return v.getOrder(); }
			});
	}

	public static Integer selectReason(String key) {
		return getTable().select(key, new xdb.TField<xbean.GsdErrorOrderInfo, Integer>() {
				public Integer get(xbean.GsdErrorOrderInfo v) { return v.getReason(); }
			});
	}

}
