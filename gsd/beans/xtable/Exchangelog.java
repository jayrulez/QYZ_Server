package xtable;

// typed table access point
public class Exchangelog {
	Exchangelog() {
	}

	public static xdb.util.AutoKey<Long> getAutoKey() {
		return _Tables_.getInstance().exchangelog.getAutoKey();
	}

	public static Long nextKey() {
		return getAutoKey().next();
	}

	public static Long insert(xbean.ExchangeLog value) {
		Long next = nextKey();
		insert(next, value);
		return next;
	}

	public static xbean.ExchangeLog get(Long key) {
		return _Tables_.getInstance().exchangelog.get(key);
	}

	public static xbean.ExchangeLog get(Long key, xbean.ExchangeLog value) {
		return _Tables_.getInstance().exchangelog.get(key, value);
	}

	public static void insert(Long key, xbean.ExchangeLog value) {
		_Tables_.getInstance().exchangelog.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().exchangelog.delete(key);
	}

	public static boolean add(Long key, xbean.ExchangeLog value) {
		return _Tables_.getInstance().exchangelog.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().exchangelog.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.ExchangeLog> getCache() {
		return _Tables_.getInstance().exchangelog.getCache();
	}

	public static xdb.TTable<Long, xbean.ExchangeLog> getTable() {
		return _Tables_.getInstance().exchangelog;
	}

	public static xbean.ExchangeLog select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.ExchangeLog, xbean.ExchangeLog>() {
			public xbean.ExchangeLog get(xbean.ExchangeLog v) { return v.toData(); }
		});
	}

	public static Long selectSeller(Long key) {
		return getTable().select(key, new xdb.TField<xbean.ExchangeLog, Long>() {
				public Long get(xbean.ExchangeLog v) { return v.getSeller(); }
			});
	}

	public static Long selectBuyer(Long key) {
		return getTable().select(key, new xdb.TField<xbean.ExchangeLog, Long>() {
				public Long get(xbean.ExchangeLog v) { return v.getBuyer(); }
			});
	}

	public static xbean.ExchangeItem selectItem(Long key) {
		return getTable().select(key, new xdb.TField<xbean.ExchangeLog, xbean.ExchangeItem>() {
				public xbean.ExchangeItem get(xbean.ExchangeLog v) { return v.getItem(); }
			});
	}

	public static Long selectTime(Long key) {
		return getTable().select(key, new xdb.TField<xbean.ExchangeLog, Long>() {
				public Long get(xbean.ExchangeLog v) { return v.getTime(); }
			});
	}

}
