package xtable;

// typed table access point
public class Roletalismandepot {
	Roletalismandepot() {
	}

	public static xbean.RoleTalismanDepot get(Long key) {
		return _Tables_.getInstance().roletalismandepot.get(key);
	}

	public static xbean.RoleTalismanDepot get(Long key, xbean.RoleTalismanDepot value) {
		return _Tables_.getInstance().roletalismandepot.get(key, value);
	}

	public static void insert(Long key, xbean.RoleTalismanDepot value) {
		_Tables_.getInstance().roletalismandepot.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().roletalismandepot.delete(key);
	}

	public static boolean add(Long key, xbean.RoleTalismanDepot value) {
		return _Tables_.getInstance().roletalismandepot.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().roletalismandepot.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.RoleTalismanDepot> getCache() {
		return _Tables_.getInstance().roletalismandepot.getCache();
	}

	public static xdb.TTable<Long, xbean.RoleTalismanDepot> getTable() {
		return _Tables_.getInstance().roletalismandepot;
	}

	public static xbean.RoleTalismanDepot select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleTalismanDepot, xbean.RoleTalismanDepot>() {
			public xbean.RoleTalismanDepot get(xbean.RoleTalismanDepot v) { return v.toData(); }
		});
	}

	public static Integer selectCapacity(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleTalismanDepot, Integer>() {
				public Integer get(xbean.RoleTalismanDepot v) { return v.getCapacity(); }
			});
	}

	public static java.util.Map<Integer, xbean.Talisman> selectTalismans(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleTalismanDepot, java.util.Map<Integer, xbean.Talisman>>() {
				public java.util.Map<Integer, xbean.Talisman> get(xbean.RoleTalismanDepot v) { return v.getTalismansAsData(); }
			});
	}

}
