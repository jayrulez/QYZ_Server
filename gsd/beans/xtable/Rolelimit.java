package xtable;

// typed table access point
public class Rolelimit {
	Rolelimit() {
	}

	public static xbean.RoleLimit get(Long key) {
		return _Tables_.getInstance().rolelimit.get(key);
	}

	public static xbean.RoleLimit get(Long key, xbean.RoleLimit value) {
		return _Tables_.getInstance().rolelimit.get(key, value);
	}

	public static void insert(Long key, xbean.RoleLimit value) {
		_Tables_.getInstance().rolelimit.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().rolelimit.delete(key);
	}

	public static boolean add(Long key, xbean.RoleLimit value) {
		return _Tables_.getInstance().rolelimit.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().rolelimit.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.RoleLimit> getCache() {
		return _Tables_.getInstance().rolelimit.getCache();
	}

	public static xdb.TTable<Long, xbean.RoleLimit> getTable() {
		return _Tables_.getInstance().rolelimit;
	}

	public static xbean.RoleLimit select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleLimit, xbean.RoleLimit>() {
			public xbean.RoleLimit get(xbean.RoleLimit v) { return v.toData(); }
		});
	}

	public static java.util.Map<Long, xbean.Limit> selectLimits(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleLimit, java.util.Map<Long, xbean.Limit>>() {
				public java.util.Map<Long, xbean.Limit> get(xbean.RoleLimit v) { return v.getLimitsAsData(); }
			});
	}

	public static java.util.Map<Long, xbean.CoolDown> selectCooldowns(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleLimit, java.util.Map<Long, xbean.CoolDown>>() {
				public java.util.Map<Long, xbean.CoolDown> get(xbean.RoleLimit v) { return v.getCooldownsAsData(); }
			});
	}

}
