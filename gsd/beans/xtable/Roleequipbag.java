package xtable;

// typed table access point
public class Roleequipbag {
	Roleequipbag() {
	}

	public static xbean.RoleEquipBag get(Long key) {
		return _Tables_.getInstance().roleequipbag.get(key);
	}

	public static xbean.RoleEquipBag get(Long key, xbean.RoleEquipBag value) {
		return _Tables_.getInstance().roleequipbag.get(key, value);
	}

	public static void insert(Long key, xbean.RoleEquipBag value) {
		_Tables_.getInstance().roleequipbag.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().roleequipbag.delete(key);
	}

	public static boolean add(Long key, xbean.RoleEquipBag value) {
		return _Tables_.getInstance().roleequipbag.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().roleequipbag.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.RoleEquipBag> getCache() {
		return _Tables_.getInstance().roleequipbag.getCache();
	}

	public static xdb.TTable<Long, xbean.RoleEquipBag> getTable() {
		return _Tables_.getInstance().roleequipbag;
	}

	public static xbean.RoleEquipBag select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleEquipBag, xbean.RoleEquipBag>() {
			public xbean.RoleEquipBag get(xbean.RoleEquipBag v) { return v.toData(); }
		});
	}

	public static Integer selectCapacity(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleEquipBag, Integer>() {
				public Integer get(xbean.RoleEquipBag v) { return v.getCapacity(); }
			});
	}

	public static java.util.Map<Integer, xbean.Equip> selectEquipmap(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleEquipBag, java.util.Map<Integer, xbean.Equip>>() {
				public java.util.Map<Integer, xbean.Equip> get(xbean.RoleEquipBag v) { return v.getEquipmapAsData(); }
			});
	}

	public static java.util.Map<Integer, xbean.Equip> selectEquiponbodymap(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleEquipBag, java.util.Map<Integer, xbean.Equip>>() {
				public java.util.Map<Integer, xbean.Equip> get(xbean.RoleEquipBag v) { return v.getEquiponbodymapAsData(); }
			});
	}

}
