package xtable;

// typed table access point
public class Userpays {
	Userpays() {
	}

	public static xbean.UserPayInfo get(Long key) {
		return _Tables_.getInstance().userpays.get(key);
	}

	public static xbean.UserPayInfo get(Long key, xbean.UserPayInfo value) {
		return _Tables_.getInstance().userpays.get(key, value);
	}

	public static void insert(Long key, xbean.UserPayInfo value) {
		_Tables_.getInstance().userpays.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().userpays.delete(key);
	}

	public static boolean add(Long key, xbean.UserPayInfo value) {
		return _Tables_.getInstance().userpays.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().userpays.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.UserPayInfo> getCache() {
		return _Tables_.getInstance().userpays.getCache();
	}

	public static xdb.TTable<Long, xbean.UserPayInfo> getTable() {
		return _Tables_.getInstance().userpays;
	}

	public static xbean.UserPayInfo select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.UserPayInfo, xbean.UserPayInfo>() {
			public xbean.UserPayInfo get(xbean.UserPayInfo v) { return v.toData(); }
		});
	}

	public static Long selectTotalpay(Long key) {
		return getTable().select(key, new xdb.TField<xbean.UserPayInfo, Long>() {
				public Long get(xbean.UserPayInfo v) { return v.getTotalpay(); }
			});
	}

	public static Long selectTotalyunbao(Long key) {
		return getTable().select(key, new xdb.TField<xbean.UserPayInfo, Long>() {
				public Long get(xbean.UserPayInfo v) { return v.getTotalyunbao(); }
			});
	}

	public static Long selectTotalbindyuanbao(Long key) {
		return getTable().select(key, new xdb.TField<xbean.UserPayInfo, Long>() {
				public Long get(xbean.UserPayInfo v) { return v.getTotalbindyuanbao(); }
			});
	}

	public static Long selectTotalvipexp(Long key) {
		return getTable().select(key, new xdb.TField<xbean.UserPayInfo, Long>() {
				public Long get(xbean.UserPayInfo v) { return v.getTotalvipexp(); }
			});
	}

	public static Boolean selectHasgotreturn(Long key) {
		return getTable().select(key, new xdb.TField<xbean.UserPayInfo, Boolean>() {
				public Boolean get(xbean.UserPayInfo v) { return v.getHasgotreturn(); }
			});
	}

	public static Long selectRoleid(Long key) {
		return getTable().select(key, new xdb.TField<xbean.UserPayInfo, Long>() {
				public Long get(xbean.UserPayInfo v) { return v.getRoleid(); }
			});
	}

}
