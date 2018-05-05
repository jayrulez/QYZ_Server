package xtable;

// typed table access point
public class Forbids {
	Forbids() {
	}

	public static xbean.Forbid get(Long key) {
		return _Tables_.getInstance().forbids.get(key);
	}

	public static xbean.Forbid get(Long key, xbean.Forbid value) {
		return _Tables_.getInstance().forbids.get(key, value);
	}

	public static void insert(Long key, xbean.Forbid value) {
		_Tables_.getInstance().forbids.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().forbids.delete(key);
	}

	public static boolean add(Long key, xbean.Forbid value) {
		return _Tables_.getInstance().forbids.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().forbids.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.Forbid> getCache() {
		return _Tables_.getInstance().forbids.getCache();
	}

	public static xdb.TTable<Long, xbean.Forbid> getTable() {
		return _Tables_.getInstance().forbids;
	}

	public static xbean.Forbid select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.Forbid, xbean.Forbid>() {
			public xbean.Forbid get(xbean.Forbid v) { return v.toData(); }
		});
	}

	public static java.util.Map<Integer, xbean.ForbidItem> selectItems(Long key) {
		return getTable().select(key, new xdb.TField<xbean.Forbid, java.util.Map<Integer, xbean.ForbidItem>>() {
				public java.util.Map<Integer, xbean.ForbidItem> get(xbean.Forbid v) { return v.getItemsAsData(); }
			});
	}

}
