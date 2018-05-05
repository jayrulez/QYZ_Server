package xtable;

// typed table access point
public class Ride {
	Ride() {
	}

	public static xbean.RoleRide get(Long key) {
		return _Tables_.getInstance().ride.get(key);
	}

	public static xbean.RoleRide get(Long key, xbean.RoleRide value) {
		return _Tables_.getInstance().ride.get(key, value);
	}

	public static void insert(Long key, xbean.RoleRide value) {
		_Tables_.getInstance().ride.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().ride.delete(key);
	}

	public static boolean add(Long key, xbean.RoleRide value) {
		return _Tables_.getInstance().ride.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().ride.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.RoleRide> getCache() {
		return _Tables_.getInstance().ride.getCache();
	}

	public static xdb.TTable<Long, xbean.RoleRide> getTable() {
		return _Tables_.getInstance().ride;
	}

	public static xbean.RoleRide select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleRide, xbean.RoleRide>() {
			public xbean.RoleRide get(xbean.RoleRide v) { return v.toData(); }
		});
	}

	public static java.util.Map<Integer, xbean.Ride> selectRides(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleRide, java.util.Map<Integer, xbean.Ride>>() {
				public java.util.Map<Integer, xbean.Ride> get(xbean.RoleRide v) { return v.getRidesAsData(); }
			});
	}

	public static Integer selectActiveride(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleRide, Integer>() {
				public Integer get(xbean.RoleRide v) { return v.getActiveride(); }
			});
	}

}
