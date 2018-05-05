package xtable;

// typed table access point
public class Roleskill {
	Roleskill() {
	}

	public static xbean.RoleSkill get(Long key) {
		return _Tables_.getInstance().roleskill.get(key);
	}

	public static xbean.RoleSkill get(Long key, xbean.RoleSkill value) {
		return _Tables_.getInstance().roleskill.get(key, value);
	}

	public static void insert(Long key, xbean.RoleSkill value) {
		_Tables_.getInstance().roleskill.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().roleskill.delete(key);
	}

	public static boolean add(Long key, xbean.RoleSkill value) {
		return _Tables_.getInstance().roleskill.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().roleskill.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.RoleSkill> getCache() {
		return _Tables_.getInstance().roleskill.getCache();
	}

	public static xdb.TTable<Long, xbean.RoleSkill> getTable() {
		return _Tables_.getInstance().roleskill;
	}

	public static xbean.RoleSkill select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleSkill, xbean.RoleSkill>() {
			public xbean.RoleSkill get(xbean.RoleSkill v) { return v.toData(); }
		});
	}

	public static java.util.Map<Integer, xbean.SkillInfo> selectSkills(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleSkill, java.util.Map<Integer, xbean.SkillInfo>>() {
				public java.util.Map<Integer, xbean.SkillInfo> get(xbean.RoleSkill v) { return v.getSkillsAsData(); }
			});
	}

	public static java.util.Map<Integer, Integer> selectEquipskillpositions(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleSkill, java.util.Map<Integer, Integer>>() {
				public java.util.Map<Integer, Integer> get(xbean.RoleSkill v) { return v.getEquipskillpositionsAsData(); }
			});
	}

}
