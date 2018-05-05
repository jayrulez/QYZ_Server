package xtable;

// typed table access point
public class Rolepays {
	Rolepays() {
	}

	public static xbean.RolePay get(Long key) {
		return _Tables_.getInstance().rolepays.get(key);
	}

	public static xbean.RolePay get(Long key, xbean.RolePay value) {
		return _Tables_.getInstance().rolepays.get(key, value);
	}

	public static void insert(Long key, xbean.RolePay value) {
		_Tables_.getInstance().rolepays.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().rolepays.delete(key);
	}

	public static boolean add(Long key, xbean.RolePay value) {
		return _Tables_.getInstance().rolepays.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().rolepays.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.RolePay> getCache() {
		return _Tables_.getInstance().rolepays.getCache();
	}

	public static xdb.TTable<Long, xbean.RolePay> getTable() {
		return _Tables_.getInstance().rolepays;
	}

	public static xbean.RolePay select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RolePay, xbean.RolePay>() {
			public xbean.RolePay get(xbean.RolePay v) { return v.toData(); }
		});
	}

	public static Boolean selectCangetfirstbonus(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RolePay, Boolean>() {
				public Boolean get(xbean.RolePay v) { return v.getCangetfirstbonus(); }
			});
	}

	public static Boolean selectIsfirstpayused(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RolePay, Boolean>() {
				public Boolean get(xbean.RolePay v) { return v.getIsfirstpayused(); }
			});
	}

	public static java.util.Map<Integer, Boolean> selectIsentryfirstpay(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RolePay, java.util.Map<Integer, Boolean>>() {
				public java.util.Map<Integer, Boolean> get(xbean.RolePay v) { return v.getIsentryfirstpayAsData(); }
			});
	}

	public static Long selectTotalpay(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RolePay, Long>() {
				public Long get(xbean.RolePay v) { return v.getTotalpay(); }
			});
	}

	public static Long selectLastrefreshtime(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RolePay, Long>() {
				public Long get(xbean.RolePay v) { return v.getLastrefreshtime(); }
			});
	}

	public static Integer selectDailypaystatus(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RolePay, Integer>() {
				public Integer get(xbean.RolePay v) { return v.getDailypaystatus(); }
			});
	}

	public static Long selectDailytotalpay(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RolePay, Long>() {
				public Long get(xbean.RolePay v) { return v.getDailytotalpay(); }
			});
	}

	public static Integer selectDailytotalpaystatus(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RolePay, Integer>() {
				public Integer get(xbean.RolePay v) { return v.getDailytotalpaystatus(); }
			});
	}

	public static Long selectRoleallpay(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RolePay, Long>() {
				public Long get(xbean.RolePay v) { return v.getRoleallpay(); }
			});
	}

	public static Integer selectDailyactivepay(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RolePay, Integer>() {
				public Integer get(xbean.RolePay v) { return v.getDailyactivepay(); }
			});
	}

	public static Boolean selectHasgotpayreturn(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RolePay, Boolean>() {
				public Boolean get(xbean.RolePay v) { return v.getHasgotpayreturn(); }
			});
	}

	public static Long selectTotalyunbao(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RolePay, Long>() {
				public Long get(xbean.RolePay v) { return v.getTotalyunbao(); }
			});
	}

	public static Long selectTotalbindyuanbao(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RolePay, Long>() {
				public Long get(xbean.RolePay v) { return v.getTotalbindyuanbao(); }
			});
	}

	public static Long selectTotalvipexp(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RolePay, Long>() {
				public Long get(xbean.RolePay v) { return v.getTotalvipexp(); }
			});
	}

}
