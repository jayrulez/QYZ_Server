package xtable;

// typed table access point
public class Exchangeitem {
	Exchangeitem() {
	}

	public static xdb.util.AutoKey<Long> getAutoKey() {
		return _Tables_.getInstance().exchangeitem.getAutoKey();
	}

	public static Long nextKey() {
		return getAutoKey().next();
	}

	public static Long insert(xbean.ExchangeItem value) {
		Long next = nextKey();
		insert(next, value);
		return next;
	}

	public static xbean.ExchangeItem get(Long key) {
		return _Tables_.getInstance().exchangeitem.get(key);
	}

	public static xbean.ExchangeItem get(Long key, xbean.ExchangeItem value) {
		return _Tables_.getInstance().exchangeitem.get(key, value);
	}

	public static void insert(Long key, xbean.ExchangeItem value) {
		_Tables_.getInstance().exchangeitem.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().exchangeitem.delete(key);
	}

	public static boolean add(Long key, xbean.ExchangeItem value) {
		return _Tables_.getInstance().exchangeitem.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().exchangeitem.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.ExchangeItem> getCache() {
		return _Tables_.getInstance().exchangeitem.getCache();
	}

	public static xdb.TTable<Long, xbean.ExchangeItem> getTable() {
		return _Tables_.getInstance().exchangeitem;
	}

	public static xbean.ExchangeItem select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.ExchangeItem, xbean.ExchangeItem>() {
			public xbean.ExchangeItem get(xbean.ExchangeItem v) { return v.toData(); }
		});
	}

	public static Long selectId(Long key) {
		return getTable().select(key, new xdb.TField<xbean.ExchangeItem, Long>() {
				public Long get(xbean.ExchangeItem v) { return v.getId(); }
			});
	}

	public static Long selectOwner(Long key) {
		return getTable().select(key, new xdb.TField<xbean.ExchangeItem, Long>() {
				public Long get(xbean.ExchangeItem v) { return v.getOwner(); }
			});
	}

	public static Integer selectPrice(Long key) {
		return getTable().select(key, new xdb.TField<xbean.ExchangeItem, Integer>() {
				public Integer get(xbean.ExchangeItem v) { return v.getPrice(); }
			});
	}

	public static Integer selectModelid(Long key) {
		return getTable().select(key, new xdb.TField<xbean.ExchangeItem, Integer>() {
				public Integer get(xbean.ExchangeItem v) { return v.getModelid(); }
			});
	}

	public static Integer selectNum(Long key) {
		return getTable().select(key, new xdb.TField<xbean.ExchangeItem, Integer>() {
				public Integer get(xbean.ExchangeItem v) { return v.getNum(); }
			});
	}

	public static Long selectExpiretime(Long key) {
		return getTable().select(key, new xdb.TField<xbean.ExchangeItem, Long>() {
				public Long get(xbean.ExchangeItem v) { return v.getExpiretime(); }
			});
	}

	public static Integer selectAnneallevel(Long key) {
		return getTable().select(key, new xdb.TField<xbean.ExchangeItem, Integer>() {
				public Integer get(xbean.ExchangeItem v) { return v.getAnneallevel(); }
			});
	}

	public static Integer selectPerfuselevel(Long key) {
		return getTable().select(key, new xdb.TField<xbean.ExchangeItem, Integer>() {
				public Integer get(xbean.ExchangeItem v) { return v.getPerfuselevel(); }
			});
	}

	public static java.util.List<xbean.AccessoryProp> selectAccessorymainprop(Long key) {
		return getTable().select(key, new xdb.TField<xbean.ExchangeItem, java.util.List<xbean.AccessoryProp>>() {
				public java.util.List<xbean.AccessoryProp> get(xbean.ExchangeItem v) { return v.getAccessorymainpropAsData(); }
			});
	}

	public static java.util.List<xbean.AccessoryProp> selectAccessoryviceprop(Long key) {
		return getTable().select(key, new xdb.TField<xbean.ExchangeItem, java.util.List<xbean.AccessoryProp>>() {
				public java.util.List<xbean.AccessoryProp> get(xbean.ExchangeItem v) { return v.getAccessoryvicepropAsData(); }
			});
	}

	public static Long selectUnshelvetime(Long key) {
		return getTable().select(key, new xdb.TField<xbean.ExchangeItem, Long>() {
				public Long get(xbean.ExchangeItem v) { return v.getUnshelvetime(); }
			});
	}

}
