package xtable;

// typed table access point
public class Roleequipdepot {
	Roleequipdepot() {
	}

	public static xbean.RoleEquipDepot get(Long key) {
		return _Tables_.getInstance().roleequipdepot.get(key);
	}

	public static xbean.RoleEquipDepot get(Long key, xbean.RoleEquipDepot value) {
		return _Tables_.getInstance().roleequipdepot.get(key, value);
	}

	public static void insert(Long key, xbean.RoleEquipDepot value) {
		_Tables_.getInstance().roleequipdepot.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().roleequipdepot.delete(key);
	}

	public static boolean add(Long key, xbean.RoleEquipDepot value) {
		return _Tables_.getInstance().roleequipdepot.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().roleequipdepot.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.RoleEquipDepot> getCache() {
		return _Tables_.getInstance().roleequipdepot.getCache();
	}

	public static xdb.TTable<Long, xbean.RoleEquipDepot> getTable() {
		return _Tables_.getInstance().roleequipdepot;
	}

	public static xbean.RoleEquipDepot select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleEquipDepot, xbean.RoleEquipDepot>() {
			public xbean.RoleEquipDepot get(xbean.RoleEquipDepot v) { return v.toData(); }
		});
	}

	public static Integer selectCapacity(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleEquipDepot, Integer>() {
				public Integer get(xbean.RoleEquipDepot v) { return v.getCapacity(); }
			});
	}

	public static java.util.NavigableMap<Integer, xbean.Equip> selectEquipbag(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleEquipDepot, java.util.NavigableMap<Integer, xbean.Equip>>() {
				public java.util.NavigableMap<Integer, xbean.Equip> get(xbean.RoleEquipDepot v) { return v.getEquipbagAsData(); }
			});
	}

}
