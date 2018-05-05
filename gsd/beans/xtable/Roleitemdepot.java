package xtable;

// typed table access point
public class Roleitemdepot {
	Roleitemdepot() {
	}

	public static xbean.RoleItemDepot get(Long key) {
		return _Tables_.getInstance().roleitemdepot.get(key);
	}

	public static xbean.RoleItemDepot get(Long key, xbean.RoleItemDepot value) {
		return _Tables_.getInstance().roleitemdepot.get(key, value);
	}

	public static void insert(Long key, xbean.RoleItemDepot value) {
		_Tables_.getInstance().roleitemdepot.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().roleitemdepot.delete(key);
	}

	public static boolean add(Long key, xbean.RoleItemDepot value) {
		return _Tables_.getInstance().roleitemdepot.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().roleitemdepot.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.RoleItemDepot> getCache() {
		return _Tables_.getInstance().roleitemdepot.getCache();
	}

	public static xdb.TTable<Long, xbean.RoleItemDepot> getTable() {
		return _Tables_.getInstance().roleitemdepot;
	}

	public static xbean.RoleItemDepot select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleItemDepot, xbean.RoleItemDepot>() {
			public xbean.RoleItemDepot get(xbean.RoleItemDepot v) { return v.toData(); }
		});
	}

	public static Integer selectCapacity(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleItemDepot, Integer>() {
				public Integer get(xbean.RoleItemDepot v) { return v.getCapacity(); }
			});
	}

	public static java.util.Map<Integer, xbean.Item> selectItems(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleItemDepot, java.util.Map<Integer, xbean.Item>>() {
				public java.util.Map<Integer, xbean.Item> get(xbean.RoleItemDepot v) { return v.getItemsAsData(); }
			});
	}

}
