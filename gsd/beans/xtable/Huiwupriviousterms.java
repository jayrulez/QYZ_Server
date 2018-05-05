package xtable;

// typed table access point
public class Huiwupriviousterms {
	Huiwupriviousterms() {
	}

	public static xbean.HuiWuPriviousTerm get(Integer key) {
		return _Tables_.getInstance().huiwupriviousterms.get(key);
	}

	public static xbean.HuiWuPriviousTerm get(Integer key, xbean.HuiWuPriviousTerm value) {
		return _Tables_.getInstance().huiwupriviousterms.get(key, value);
	}

	public static void insert(Integer key, xbean.HuiWuPriviousTerm value) {
		_Tables_.getInstance().huiwupriviousterms.insert(key, value);
	}

	public static void delete(Integer key) {
		_Tables_.getInstance().huiwupriviousterms.delete(key);
	}

	public static boolean add(Integer key, xbean.HuiWuPriviousTerm value) {
		return _Tables_.getInstance().huiwupriviousterms.add(key, value);
	}

	public static boolean remove(Integer key) {
		return _Tables_.getInstance().huiwupriviousterms.remove(key);
	}

	public static xdb.TTableCache<Integer, xbean.HuiWuPriviousTerm> getCache() {
		return _Tables_.getInstance().huiwupriviousterms.getCache();
	}

	public static xdb.TTable<Integer, xbean.HuiWuPriviousTerm> getTable() {
		return _Tables_.getInstance().huiwupriviousterms;
	}

	public static xbean.HuiWuPriviousTerm select(Integer key) {
		return getTable().select(key, new xdb.TField<xbean.HuiWuPriviousTerm, xbean.HuiWuPriviousTerm>() {
			public xbean.HuiWuPriviousTerm get(xbean.HuiWuPriviousTerm v) { return v.toData(); }
		});
	}

	public static Integer selectTermid(Integer key) {
		return getTable().select(key, new xdb.TField<xbean.HuiWuPriviousTerm, Integer>() {
				public Integer get(xbean.HuiWuPriviousTerm v) { return v.getTermid(); }
			});
	}

	public static Long selectOpentime(Integer key) {
		return getTable().select(key, new xdb.TField<xbean.HuiWuPriviousTerm, Long>() {
				public Long get(xbean.HuiWuPriviousTerm v) { return v.getOpentime(); }
			});
	}

	public static Long selectEndtime(Integer key) {
		return getTable().select(key, new xdb.TField<xbean.HuiWuPriviousTerm, Long>() {
				public Long get(xbean.HuiWuPriviousTerm v) { return v.getEndtime(); }
			});
	}

	public static java.util.Map<Integer, xbean.HuiWuChampion> selectChampions(Integer key) {
		return getTable().select(key, new xdb.TField<xbean.HuiWuPriviousTerm, java.util.Map<Integer, xbean.HuiWuChampion>>() {
				public java.util.Map<Integer, xbean.HuiWuChampion> get(xbean.HuiWuPriviousTerm v) { return v.getChampionsAsData(); }
			});
	}

}
