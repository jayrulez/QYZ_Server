package xtable;

// typed table access point
public class Jade {
	Jade() {
	}

	public static xbean.RoleJadeInfo get(Long key) {
		return _Tables_.getInstance().jade.get(key);
	}

	public static xbean.RoleJadeInfo get(Long key, xbean.RoleJadeInfo value) {
		return _Tables_.getInstance().jade.get(key, value);
	}

	public static void insert(Long key, xbean.RoleJadeInfo value) {
		_Tables_.getInstance().jade.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().jade.delete(key);
	}

	public static boolean add(Long key, xbean.RoleJadeInfo value) {
		return _Tables_.getInstance().jade.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().jade.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.RoleJadeInfo> getCache() {
		return _Tables_.getInstance().jade.getCache();
	}

	public static xdb.TTable<Long, xbean.RoleJadeInfo> getTable() {
		return _Tables_.getInstance().jade;
	}

	public static xbean.RoleJadeInfo select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleJadeInfo, xbean.RoleJadeInfo>() {
			public xbean.RoleJadeInfo get(xbean.RoleJadeInfo v) { return v.toData(); }
		});
	}

	public static Integer selectLevel(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleJadeInfo, Integer>() {
				public Integer get(xbean.RoleJadeInfo v) { return v.getLevel(); }
			});
	}

	public static Integer selectBonus(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleJadeInfo, Integer>() {
				public Integer get(xbean.RoleJadeInfo v) { return v.getBonus(); }
			});
	}

	public static Integer selectHolenum(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleJadeInfo, Integer>() {
				public Integer get(xbean.RoleJadeInfo v) { return v.getHolenum(); }
			});
	}

	public static java.util.Map<Integer, xbean.Jewelry> selectJewelry(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleJadeInfo, java.util.Map<Integer, xbean.Jewelry>>() {
				public java.util.Map<Integer, xbean.Jewelry> get(xbean.RoleJadeInfo v) { return v.getJewelryAsData(); }
			});
	}

	public static java.util.List<xbean.Jewelry> selectJewelrybag(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleJadeInfo, java.util.List<xbean.Jewelry>>() {
				public java.util.List<xbean.Jewelry> get(xbean.RoleJadeInfo v) { return v.getJewelrybagAsData(); }
			});
	}

	public static Integer selectJewelrygetlevel(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleJadeInfo, Integer>() {
				public Integer get(xbean.RoleJadeInfo v) { return v.getJewelrygetlevel(); }
			});
	}

}
