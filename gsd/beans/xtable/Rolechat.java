package xtable;

// typed table access point
public class Rolechat {
	Rolechat() {
	}

	public static xbean.RoleChat get(Long key) {
		return _Tables_.getInstance().rolechat.get(key);
	}

	public static xbean.RoleChat get(Long key, xbean.RoleChat value) {
		return _Tables_.getInstance().rolechat.get(key, value);
	}

	public static void insert(Long key, xbean.RoleChat value) {
		_Tables_.getInstance().rolechat.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().rolechat.delete(key);
	}

	public static boolean add(Long key, xbean.RoleChat value) {
		return _Tables_.getInstance().rolechat.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().rolechat.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.RoleChat> getCache() {
		return _Tables_.getInstance().rolechat.getCache();
	}

	public static xdb.TTable<Long, xbean.RoleChat> getTable() {
		return _Tables_.getInstance().rolechat;
	}

	public static xbean.RoleChat select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleChat, xbean.RoleChat>() {
			public xbean.RoleChat get(xbean.RoleChat v) { return v.toData(); }
		});
	}

	public static java.util.Set<String> selectChatface(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleChat, java.util.Set<String>>() {
				public java.util.Set<String> get(xbean.RoleChat v) { return v.getChatfaceAsData(); }
			});
	}

}
