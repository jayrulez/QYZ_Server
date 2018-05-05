package xtable;

// typed table access point
public class Globallactivity {
	Globallactivity() {
	}

	public static xbean.GlobalAllActivity get(Integer key) {
		return _Tables_.getInstance().globallactivity.get(key);
	}

	public static xbean.GlobalAllActivity get(Integer key, xbean.GlobalAllActivity value) {
		return _Tables_.getInstance().globallactivity.get(key, value);
	}

	public static void insert(Integer key, xbean.GlobalAllActivity value) {
		_Tables_.getInstance().globallactivity.insert(key, value);
	}

	public static void delete(Integer key) {
		_Tables_.getInstance().globallactivity.delete(key);
	}

	public static boolean add(Integer key, xbean.GlobalAllActivity value) {
		return _Tables_.getInstance().globallactivity.add(key, value);
	}

	public static boolean remove(Integer key) {
		return _Tables_.getInstance().globallactivity.remove(key);
	}

	public static xdb.TTableCache<Integer, xbean.GlobalAllActivity> getCache() {
		return _Tables_.getInstance().globallactivity.getCache();
	}

	public static xdb.TTable<Integer, xbean.GlobalAllActivity> getTable() {
		return _Tables_.getInstance().globallactivity;
	}

	public static xbean.GlobalAllActivity select(Integer key) {
		return getTable().select(key, new xdb.TField<xbean.GlobalAllActivity, xbean.GlobalAllActivity>() {
			public xbean.GlobalAllActivity get(xbean.GlobalAllActivity v) { return v.toData(); }
		});
	}

	public static java.util.Map<Integer, xbean.GlobalActivityOpenInfos> selectOpeninfos(Integer key) {
		return getTable().select(key, new xdb.TField<xbean.GlobalAllActivity, java.util.Map<Integer, xbean.GlobalActivityOpenInfos>>() {
				public java.util.Map<Integer, xbean.GlobalActivityOpenInfos> get(xbean.GlobalAllActivity v) { return v.getOpeninfosAsData(); }
			});
	}

	public static java.util.Map<Integer, xbean.GlobalActivity> selectDatas(Integer key) {
		return getTable().select(key, new xdb.TField<xbean.GlobalAllActivity, java.util.Map<Integer, xbean.GlobalActivity>>() {
				public java.util.Map<Integer, xbean.GlobalActivity> get(xbean.GlobalAllActivity v) { return v.getDatasAsData(); }
			});
	}

}
