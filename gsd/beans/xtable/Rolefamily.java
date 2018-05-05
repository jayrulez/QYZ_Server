package xtable;

// typed table access point
public class Rolefamily {
	Rolefamily() {
	}

	public static xbean.RoleFamily get(Long key) {
		return _Tables_.getInstance().rolefamily.get(key);
	}

	public static xbean.RoleFamily get(Long key, xbean.RoleFamily value) {
		return _Tables_.getInstance().rolefamily.get(key, value);
	}

	public static void insert(Long key, xbean.RoleFamily value) {
		_Tables_.getInstance().rolefamily.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().rolefamily.delete(key);
	}

	public static boolean add(Long key, xbean.RoleFamily value) {
		return _Tables_.getInstance().rolefamily.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().rolefamily.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.RoleFamily> getCache() {
		return _Tables_.getInstance().rolefamily.getCache();
	}

	public static xdb.TTable<Long, xbean.RoleFamily> getTable() {
		return _Tables_.getInstance().rolefamily;
	}

	public static xbean.RoleFamily select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleFamily, xbean.RoleFamily>() {
			public xbean.RoleFamily get(xbean.RoleFamily v) { return v.toData(); }
		});
	}

	public static Long selectCurrentfid(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleFamily, Long>() {
				public Long get(xbean.RoleFamily v) { return v.getCurrentfid(); }
			});
	}

	public static java.util.Map<Long, Long> selectRequestedfamily(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleFamily, java.util.Map<Long, Long>>() {
				public java.util.Map<Long, Long> get(xbean.RoleFamily v) { return v.getRequestedfamilyAsData(); }
			});
	}

	public static Long selectLastquittime(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleFamily, Long>() {
				public Long get(xbean.RoleFamily v) { return v.getLastquittime(); }
			});
	}

	public static Integer selectTotalquitnum(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleFamily, Integer>() {
				public Integer get(xbean.RoleFamily v) { return v.getTotalquitnum(); }
			});
	}

	public static Integer selectHasjoinpartytoday(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleFamily, Integer>() {
				public Integer get(xbean.RoleFamily v) { return v.getHasjoinpartytoday(); }
			});
	}

	public static Integer selectIsfindbackparty(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleFamily, Integer>() {
				public Integer get(xbean.RoleFamily v) { return v.getIsfindbackparty(); }
			});
	}

}
