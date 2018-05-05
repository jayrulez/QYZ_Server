package xtable;

// typed table access point
public class Apnsdevices {
	Apnsdevices() {
	}

	public static xbean.ApnsDevice get(Long key) {
		return _Tables_.getInstance().apnsdevices.get(key);
	}

	public static xbean.ApnsDevice get(Long key, xbean.ApnsDevice value) {
		return _Tables_.getInstance().apnsdevices.get(key, value);
	}

	public static void insert(Long key, xbean.ApnsDevice value) {
		_Tables_.getInstance().apnsdevices.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().apnsdevices.delete(key);
	}

	public static boolean add(Long key, xbean.ApnsDevice value) {
		return _Tables_.getInstance().apnsdevices.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().apnsdevices.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.ApnsDevice> getCache() {
		return _Tables_.getInstance().apnsdevices.getCache();
	}

	public static xdb.TTable<Long, xbean.ApnsDevice> getTable() {
		return _Tables_.getInstance().apnsdevices;
	}

	public static xbean.ApnsDevice select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.ApnsDevice, xbean.ApnsDevice>() {
			public xbean.ApnsDevice get(xbean.ApnsDevice v) { return v.toData(); }
		});
	}

	public static String selectToken(Long key) {
		return getTable().select(key, new xdb.TField<xbean.ApnsDevice, String>() {
				public String get(xbean.ApnsDevice v) { return v.getToken(); }
			});
	}

	public static Long selectUpdatetime(Long key) {
		return getTable().select(key, new xdb.TField<xbean.ApnsDevice, Long>() {
				public Long get(xbean.ApnsDevice v) { return v.getUpdatetime(); }
			});
	}

}
