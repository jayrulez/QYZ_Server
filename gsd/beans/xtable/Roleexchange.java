package xtable;

// typed table access point
public class Roleexchange {
	Roleexchange() {
	}

	public static xbean.RoleExchange get(Long key) {
		return _Tables_.getInstance().roleexchange.get(key);
	}

	public static xbean.RoleExchange get(Long key, xbean.RoleExchange value) {
		return _Tables_.getInstance().roleexchange.get(key, value);
	}

	public static void insert(Long key, xbean.RoleExchange value) {
		_Tables_.getInstance().roleexchange.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().roleexchange.delete(key);
	}

	public static boolean add(Long key, xbean.RoleExchange value) {
		return _Tables_.getInstance().roleexchange.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().roleexchange.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.RoleExchange> getCache() {
		return _Tables_.getInstance().roleexchange.getCache();
	}

	public static xdb.TTable<Long, xbean.RoleExchange> getTable() {
		return _Tables_.getInstance().roleexchange;
	}

	public static xbean.RoleExchange select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleExchange, xbean.RoleExchange>() {
			public xbean.RoleExchange get(xbean.RoleExchange v) { return v.toData(); }
		});
	}

	public static java.util.List<Long> selectItems(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleExchange, java.util.List<Long>>() {
				public java.util.List<Long> get(xbean.RoleExchange v) { return v.getItemsAsData(); }
			});
	}

	public static java.util.List<Long> selectLogs(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleExchange, java.util.List<Long>>() {
				public java.util.List<Long> get(xbean.RoleExchange v) { return v.getLogsAsData(); }
			});
	}

}
