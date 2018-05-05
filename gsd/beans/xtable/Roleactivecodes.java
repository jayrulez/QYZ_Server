package xtable;

// typed table access point
public class Roleactivecodes {
	Roleactivecodes() {
	}

	public static xbean.RoleActiveCode get(Long key) {
		return _Tables_.getInstance().roleactivecodes.get(key);
	}

	public static xbean.RoleActiveCode get(Long key, xbean.RoleActiveCode value) {
		return _Tables_.getInstance().roleactivecodes.get(key, value);
	}

	public static void insert(Long key, xbean.RoleActiveCode value) {
		_Tables_.getInstance().roleactivecodes.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().roleactivecodes.delete(key);
	}

	public static boolean add(Long key, xbean.RoleActiveCode value) {
		return _Tables_.getInstance().roleactivecodes.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().roleactivecodes.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.RoleActiveCode> getCache() {
		return _Tables_.getInstance().roleactivecodes.getCache();
	}

	public static xdb.TTable<Long, xbean.RoleActiveCode> getTable() {
		return _Tables_.getInstance().roleactivecodes;
	}

	public static xbean.RoleActiveCode select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleActiveCode, xbean.RoleActiveCode>() {
			public xbean.RoleActiveCode get(xbean.RoleActiveCode v) { return v.toData(); }
		});
	}

	public static java.util.Map<Integer, xbean.ActiveCode> selectCodegroups(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleActiveCode, java.util.Map<Integer, xbean.ActiveCode>>() {
				public java.util.Map<Integer, xbean.ActiveCode> get(xbean.RoleActiveCode v) { return v.getCodegroupsAsData(); }
			});
	}

}
