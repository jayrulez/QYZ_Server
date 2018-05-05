package xtable;

// typed table access point
public class Roleitembag {
	Roleitembag() {
	}

	public static xbean.RoleItemBag get(Long key) {
		return _Tables_.getInstance().roleitembag.get(key);
	}

	public static xbean.RoleItemBag get(Long key, xbean.RoleItemBag value) {
		return _Tables_.getInstance().roleitembag.get(key, value);
	}

	public static void insert(Long key, xbean.RoleItemBag value) {
		_Tables_.getInstance().roleitembag.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().roleitembag.delete(key);
	}

	public static boolean add(Long key, xbean.RoleItemBag value) {
		return _Tables_.getInstance().roleitembag.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().roleitembag.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.RoleItemBag> getCache() {
		return _Tables_.getInstance().roleitembag.getCache();
	}

	public static xdb.TTable<Long, xbean.RoleItemBag> getTable() {
		return _Tables_.getInstance().roleitembag;
	}

	public static xbean.RoleItemBag select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleItemBag, xbean.RoleItemBag>() {
			public xbean.RoleItemBag get(xbean.RoleItemBag v) { return v.toData(); }
		});
	}

	public static Integer selectCapacity(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleItemBag, Integer>() {
				public Integer get(xbean.RoleItemBag v) { return v.getCapacity(); }
			});
	}

	public static java.util.Map<Integer, xbean.Item> selectItems(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleItemBag, java.util.Map<Integer, xbean.Item>>() {
				public java.util.Map<Integer, xbean.Item> get(xbean.RoleItemBag v) { return v.getItemsAsData(); }
			});
	}

}
