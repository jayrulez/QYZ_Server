package xtable;

// typed table access point
public class Rolemarriageinfo {
	Rolemarriageinfo() {
	}

	public static xbean.RoleMarriage get(Long key) {
		return _Tables_.getInstance().rolemarriageinfo.get(key);
	}

	public static xbean.RoleMarriage get(Long key, xbean.RoleMarriage value) {
		return _Tables_.getInstance().rolemarriageinfo.get(key, value);
	}

	public static void insert(Long key, xbean.RoleMarriage value) {
		_Tables_.getInstance().rolemarriageinfo.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().rolemarriageinfo.delete(key);
	}

	public static boolean add(Long key, xbean.RoleMarriage value) {
		return _Tables_.getInstance().rolemarriageinfo.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().rolemarriageinfo.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.RoleMarriage> getCache() {
		return _Tables_.getInstance().rolemarriageinfo.getCache();
	}

	public static xdb.TTable<Long, xbean.RoleMarriage> getTable() {
		return _Tables_.getInstance().rolemarriageinfo;
	}

	public static xbean.RoleMarriage select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleMarriage, xbean.RoleMarriage>() {
			public xbean.RoleMarriage get(xbean.RoleMarriage v) { return v.toData(); }
		});
	}

	public static Long selectCoupleroleid(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleMarriage, Long>() {
				public Long get(xbean.RoleMarriage v) { return v.getCoupleroleid(); }
			});
	}

	public static Long selectCurproposeid(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleMarriage, Long>() {
				public Long get(xbean.RoleMarriage v) { return v.getCurproposeid(); }
			});
	}

	public static Long selectStartproposetime(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleMarriage, Long>() {
				public Long get(xbean.RoleMarriage v) { return v.getStartproposetime(); }
			});
	}

	public static java.util.List<Long> selectWishfriendlist(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleMarriage, java.util.List<Long>>() {
				public java.util.List<Long> get(xbean.RoleMarriage v) { return v.getWishfriendlistAsData(); }
			});
	}

}
