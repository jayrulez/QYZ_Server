package xtable;

// typed table access point
public class Processingorders {
	Processingorders() {
	}

	public static xbean.AppOrder get(Long key) {
		return _Tables_.getInstance().processingorders.get(key);
	}

	public static xbean.AppOrder get(Long key, xbean.AppOrder value) {
		return _Tables_.getInstance().processingorders.get(key, value);
	}

	public static void insert(Long key, xbean.AppOrder value) {
		_Tables_.getInstance().processingorders.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().processingorders.delete(key);
	}

	public static boolean add(Long key, xbean.AppOrder value) {
		return _Tables_.getInstance().processingorders.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().processingorders.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.AppOrder> getCache() {
		return _Tables_.getInstance().processingorders.getCache();
	}

	public static xdb.TTable<Long, xbean.AppOrder> getTable() {
		return _Tables_.getInstance().processingorders;
	}

	public static xbean.AppOrder select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.AppOrder, xbean.AppOrder>() {
			public xbean.AppOrder get(xbean.AppOrder v) { return v.toData(); }
		});
	}

	public static Long selectRoleid(Long key) {
		return getTable().select(key, new xdb.TField<xbean.AppOrder, Long>() {
				public Long get(xbean.AppOrder v) { return v.getRoleid(); }
			});
	}

	public static Integer selectProductid(Long key) {
		return getTable().select(key, new xdb.TField<xbean.AppOrder, Integer>() {
				public Integer get(xbean.AppOrder v) { return v.getProductid(); }
			});
	}

	public static Long selectTime(Long key) {
		return getTable().select(key, new xdb.TField<xbean.AppOrder, Long>() {
				public Long get(xbean.AppOrder v) { return v.getTime(); }
			});
	}

}
