package xtable;

// typed table access point
public class Roleattrs {
	Roleattrs() {
	}

	public static xbean.RoleAttr get(Long key) {
		return _Tables_.getInstance().roleattrs.get(key);
	}

	public static xbean.RoleAttr get(Long key, xbean.RoleAttr value) {
		return _Tables_.getInstance().roleattrs.get(key, value);
	}

	public static void insert(Long key, xbean.RoleAttr value) {
		_Tables_.getInstance().roleattrs.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().roleattrs.delete(key);
	}

	public static boolean add(Long key, xbean.RoleAttr value) {
		return _Tables_.getInstance().roleattrs.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().roleattrs.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.RoleAttr> getCache() {
		return _Tables_.getInstance().roleattrs.getCache();
	}

	public static xdb.TTable<Long, xbean.RoleAttr> getTable() {
		return _Tables_.getInstance().roleattrs;
	}

	public static xbean.RoleAttr select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleAttr, xbean.RoleAttr>() {
			public xbean.RoleAttr get(xbean.RoleAttr v) { return v.toData(); }
		});
	}

	public static Long selectRoleid(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleAttr, Long>() {
				public Long get(xbean.RoleAttr v) { return v.getRoleid(); }
			});
	}

	public static Integer selectRolecombatpower(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleAttr, Integer>() {
				public Integer get(xbean.RoleAttr v) { return v.getRolecombatpower(); }
			});
	}

	public static java.util.List<Float> selectRawattrs(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleAttr, java.util.List<Float>>() {
				public java.util.List<Float> get(xbean.RoleAttr v) { return v.getRawattrsAsData(); }
			});
	}

	public static java.util.List<Float> selectFinalattrs(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleAttr, java.util.List<Float>>() {
				public java.util.List<Float> get(xbean.RoleAttr v) { return v.getFinalattrsAsData(); }
			});
	}

	public static java.util.Map<String, xbean.GroupAttr> selectGroupattrs(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleAttr, java.util.Map<String, xbean.GroupAttr>>() {
				public java.util.Map<String, xbean.GroupAttr> get(xbean.RoleAttr v) { return v.getGroupattrsAsData(); }
			});
	}

	public static Integer selectHp(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleAttr, Integer>() {
				public Integer get(xbean.RoleAttr v) { return v.getHp(); }
			});
	}

	public static Integer selectMp(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleAttr, Integer>() {
				public Integer get(xbean.RoleAttr v) { return v.getMp(); }
			});
	}

	public static java.util.Map<Integer, Long> selectSkillcolddowns(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleAttr, java.util.Map<Integer, Long>>() {
				public java.util.Map<Integer, Long> get(xbean.RoleAttr v) { return v.getSkillcolddownsAsData(); }
			});
	}

	public static Integer selectResethpmp(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleAttr, Integer>() {
				public Integer get(xbean.RoleAttr v) { return v.getResethpmp(); }
			});
	}

	public static Integer selectPetcombatpower(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleAttr, Integer>() {
				public Integer get(xbean.RoleAttr v) { return v.getPetcombatpower(); }
			});
	}

	public static Integer selectTotalcombatpower(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleAttr, Integer>() {
				public Integer get(xbean.RoleAttr v) { return v.getTotalcombatpower(); }
			});
	}

}
