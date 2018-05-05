package xtable;

// typed table access point
public class Roletalismanbag {
	Roletalismanbag() {
	}

	public static xbean.RoleTalismanBag get(Long key) {
		return _Tables_.getInstance().roletalismanbag.get(key);
	}

	public static xbean.RoleTalismanBag get(Long key, xbean.RoleTalismanBag value) {
		return _Tables_.getInstance().roletalismanbag.get(key, value);
	}

	public static void insert(Long key, xbean.RoleTalismanBag value) {
		_Tables_.getInstance().roletalismanbag.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().roletalismanbag.delete(key);
	}

	public static boolean add(Long key, xbean.RoleTalismanBag value) {
		return _Tables_.getInstance().roletalismanbag.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().roletalismanbag.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.RoleTalismanBag> getCache() {
		return _Tables_.getInstance().roletalismanbag.getCache();
	}

	public static xdb.TTable<Long, xbean.RoleTalismanBag> getTable() {
		return _Tables_.getInstance().roletalismanbag;
	}

	public static xbean.RoleTalismanBag select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleTalismanBag, xbean.RoleTalismanBag>() {
			public xbean.RoleTalismanBag get(xbean.RoleTalismanBag v) { return v.toData(); }
		});
	}

	public static Integer selectCapacity(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleTalismanBag, Integer>() {
				public Integer get(xbean.RoleTalismanBag v) { return v.getCapacity(); }
			});
	}

	public static java.util.Map<Integer, xbean.Talisman> selectTalismans(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleTalismanBag, java.util.Map<Integer, xbean.Talisman>>() {
				public java.util.Map<Integer, xbean.Talisman> get(xbean.RoleTalismanBag v) { return v.getTalismansAsData(); }
			});
	}

	public static java.util.Map<Integer, xbean.Talisman> selectEquipedtalismans(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleTalismanBag, java.util.Map<Integer, xbean.Talisman>>() {
				public java.util.Map<Integer, xbean.Talisman> get(xbean.RoleTalismanBag v) { return v.getEquipedtalismansAsData(); }
			});
	}

	public static Integer selectLuckytype(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleTalismanBag, Integer>() {
				public Integer get(xbean.RoleTalismanBag v) { return v.getLuckytype(); }
			});
	}

	public static Integer selectLuckywashtimes(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleTalismanBag, Integer>() {
				public Integer get(xbean.RoleTalismanBag v) { return v.getLuckywashtimes(); }
			});
	}

	public static Integer selectMaxcombatpower(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleTalismanBag, Integer>() {
				public Integer get(xbean.RoleTalismanBag v) { return v.getMaxcombatpower(); }
			});
	}

}
