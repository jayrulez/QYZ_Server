package xtable;

// typed table access point
public class Gmsenseword {
	Gmsenseword() {
	}

	public static xbean.GMSenseword get(Integer key) {
		return _Tables_.getInstance().gmsenseword.get(key);
	}

	public static xbean.GMSenseword get(Integer key, xbean.GMSenseword value) {
		return _Tables_.getInstance().gmsenseword.get(key, value);
	}

	public static void insert(Integer key, xbean.GMSenseword value) {
		_Tables_.getInstance().gmsenseword.insert(key, value);
	}

	public static void delete(Integer key) {
		_Tables_.getInstance().gmsenseword.delete(key);
	}

	public static boolean add(Integer key, xbean.GMSenseword value) {
		return _Tables_.getInstance().gmsenseword.add(key, value);
	}

	public static boolean remove(Integer key) {
		return _Tables_.getInstance().gmsenseword.remove(key);
	}

	public static xdb.TTableCache<Integer, xbean.GMSenseword> getCache() {
		return _Tables_.getInstance().gmsenseword.getCache();
	}

	public static xdb.TTable<Integer, xbean.GMSenseword> getTable() {
		return _Tables_.getInstance().gmsenseword;
	}

	public static xbean.GMSenseword select(Integer key) {
		return getTable().select(key, new xdb.TField<xbean.GMSenseword, xbean.GMSenseword>() {
			public xbean.GMSenseword get(xbean.GMSenseword v) { return v.toData(); }
		});
	}

	public static java.util.Set<String> selectAddwords(Integer key) {
		return getTable().select(key, new xdb.TField<xbean.GMSenseword, java.util.Set<String>>() {
				public java.util.Set<String> get(xbean.GMSenseword v) { return v.getAddwordsAsData(); }
			});
	}

	public static java.util.Set<String> selectRemovewords(Integer key) {
		return getTable().select(key, new xdb.TField<xbean.GMSenseword, java.util.Set<String>>() {
				public java.util.Set<String> get(xbean.GMSenseword v) { return v.getRemovewordsAsData(); }
			});
	}

}
