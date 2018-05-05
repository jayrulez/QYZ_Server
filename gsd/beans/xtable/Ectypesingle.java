package xtable;

// typed table access point
public class Ectypesingle {
	Ectypesingle() {
	}

	public static xbean.EctypeSingle get(Integer key) {
		return _Tables_.getInstance().ectypesingle.get(key);
	}

	public static xbean.EctypeSingle get(Integer key, xbean.EctypeSingle value) {
		return _Tables_.getInstance().ectypesingle.get(key, value);
	}

	public static void insert(Integer key, xbean.EctypeSingle value) {
		_Tables_.getInstance().ectypesingle.insert(key, value);
	}

	public static void delete(Integer key) {
		_Tables_.getInstance().ectypesingle.delete(key);
	}

	public static boolean add(Integer key, xbean.EctypeSingle value) {
		return _Tables_.getInstance().ectypesingle.add(key, value);
	}

	public static boolean remove(Integer key) {
		return _Tables_.getInstance().ectypesingle.remove(key);
	}

	public static xdb.TTableCache<Integer, xbean.EctypeSingle> getCache() {
		return _Tables_.getInstance().ectypesingle.getCache();
	}

	public static xdb.TTable<Integer, xbean.EctypeSingle> getTable() {
		return _Tables_.getInstance().ectypesingle;
	}

	public static xbean.EctypeSingle select(Integer key) {
		return getTable().select(key, new xdb.TField<xbean.EctypeSingle, xbean.EctypeSingle>() {
			public xbean.EctypeSingle get(xbean.EctypeSingle v) { return v.toData(); }
		});
	}

	public static java.util.Map<Integer, xbean.DailyEctypeRecord> selectDailyectypebestrecords(Integer key) {
		return getTable().select(key, new xdb.TField<xbean.EctypeSingle, java.util.Map<Integer, xbean.DailyEctypeRecord>>() {
				public java.util.Map<Integer, xbean.DailyEctypeRecord> get(xbean.EctypeSingle v) { return v.getDailyectypebestrecordsAsData(); }
			});
	}

}
