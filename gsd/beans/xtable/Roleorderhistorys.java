package xtable;

// typed table access point
public class Roleorderhistorys {
	Roleorderhistorys() {
	}

	public static xbean.RoleOrderHistroy get(Long key) {
		return _Tables_.getInstance().roleorderhistorys.get(key);
	}

	public static xbean.RoleOrderHistroy get(Long key, xbean.RoleOrderHistroy value) {
		return _Tables_.getInstance().roleorderhistorys.get(key, value);
	}

	public static void insert(Long key, xbean.RoleOrderHistroy value) {
		_Tables_.getInstance().roleorderhistorys.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().roleorderhistorys.delete(key);
	}

	public static boolean add(Long key, xbean.RoleOrderHistroy value) {
		return _Tables_.getInstance().roleorderhistorys.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().roleorderhistorys.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.RoleOrderHistroy> getCache() {
		return _Tables_.getInstance().roleorderhistorys.getCache();
	}

	public static xdb.TTable<Long, xbean.RoleOrderHistroy> getTable() {
		return _Tables_.getInstance().roleorderhistorys;
	}

	public static xbean.RoleOrderHistroy select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleOrderHistroy, xbean.RoleOrderHistroy>() {
			public xbean.RoleOrderHistroy get(xbean.RoleOrderHistroy v) { return v.toData(); }
		});
	}

	public static java.util.Map<Long, xbean.AppOrder> selectSucceedorder(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleOrderHistroy, java.util.Map<Long, xbean.AppOrder>>() {
				public java.util.Map<Long, xbean.AppOrder> get(xbean.RoleOrderHistroy v) { return v.getSucceedorderAsData(); }
			});
	}

	public static java.util.Map<Long, xbean.AppOrder> selectTimeoutorder(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleOrderHistroy, java.util.Map<Long, xbean.AppOrder>>() {
				public java.util.Map<Long, xbean.AppOrder> get(xbean.RoleOrderHistroy v) { return v.getTimeoutorderAsData(); }
			});
	}

}
