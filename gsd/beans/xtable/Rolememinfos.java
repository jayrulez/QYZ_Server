package xtable;

// typed table access point
public class Rolememinfos {
	Rolememinfos() {
	}

	public static xbean.RoleMemInfo get(Long key) {
		return _Tables_.getInstance().rolememinfos.get(key);
	}

	public static xbean.RoleMemInfo get(Long key, xbean.RoleMemInfo value) {
		return _Tables_.getInstance().rolememinfos.get(key, value);
	}

	public static void insert(Long key, xbean.RoleMemInfo value) {
		_Tables_.getInstance().rolememinfos.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().rolememinfos.delete(key);
	}

	public static boolean add(Long key, xbean.RoleMemInfo value) {
		return _Tables_.getInstance().rolememinfos.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().rolememinfos.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.RoleMemInfo> getCache() {
		return _Tables_.getInstance().rolememinfos.getCache();
	}

	public static xdb.TTable<Long, xbean.RoleMemInfo> getTable() {
		return _Tables_.getInstance().rolememinfos;
	}

	public static xbean.RoleMemInfo select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleMemInfo, xbean.RoleMemInfo>() {
			public xbean.RoleMemInfo get(xbean.RoleMemInfo v) { return v.toData(); }
		});
	}

	public static java.util.Map<Integer, xbean.HeroesGroupMemInfo> selectHerogroupmeminfos(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleMemInfo, java.util.Map<Integer, xbean.HeroesGroupMemInfo>>() {
				public java.util.Map<Integer, xbean.HeroesGroupMemInfo> get(xbean.RoleMemInfo v) { return v.getHerogroupmeminfosAsData(); }
			});
	}

}
