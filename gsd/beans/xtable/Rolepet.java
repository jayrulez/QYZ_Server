package xtable;

// typed table access point
public class Rolepet {
	Rolepet() {
	}

	public static xbean.RolePet get(Long key) {
		return _Tables_.getInstance().rolepet.get(key);
	}

	public static xbean.RolePet get(Long key, xbean.RolePet value) {
		return _Tables_.getInstance().rolepet.get(key, value);
	}

	public static void insert(Long key, xbean.RolePet value) {
		_Tables_.getInstance().rolepet.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().rolepet.delete(key);
	}

	public static boolean add(Long key, xbean.RolePet value) {
		return _Tables_.getInstance().rolepet.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().rolepet.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.RolePet> getCache() {
		return _Tables_.getInstance().rolepet.getCache();
	}

	public static xdb.TTable<Long, xbean.RolePet> getTable() {
		return _Tables_.getInstance().rolepet;
	}

	public static xbean.RolePet select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RolePet, xbean.RolePet>() {
			public xbean.RolePet get(xbean.RolePet v) { return v.toData(); }
		});
	}

	public static java.util.Map<Integer, xbean.Pet> selectPetmap(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RolePet, java.util.Map<Integer, xbean.Pet>>() {
				public java.util.Map<Integer, xbean.Pet> get(xbean.RolePet v) { return v.getPetmapAsData(); }
			});
	}

	public static java.util.List<Integer> selectFightpets(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RolePet, java.util.List<Integer>>() {
				public java.util.List<Integer> get(xbean.RolePet v) { return v.getFightpetsAsData(); }
			});
	}

	public static Integer selectActivepetmodelid(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RolePet, Integer>() {
				public Integer get(xbean.RolePet v) { return v.getActivepetmodelid(); }
			});
	}

	public static java.util.Map<Integer, Integer> selectPetfragmentmap(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RolePet, java.util.Map<Integer, Integer>>() {
				public java.util.Map<Integer, Integer> get(xbean.RolePet v) { return v.getPetfragmentmapAsData(); }
			});
	}

	public static Integer selectTotalcombatpower(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RolePet, Integer>() {
				public Integer get(xbean.RolePet v) { return v.getTotalcombatpower(); }
			});
	}

}
