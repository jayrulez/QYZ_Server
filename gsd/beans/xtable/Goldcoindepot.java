package xtable;

// typed table access point
public class Goldcoindepot {
	Goldcoindepot() {
	}

	public static Long get(Long key) {
		return _Tables_.getInstance().goldcoindepot.get(key);
	}

	public static Long get(Long key, Long value) {
		return _Tables_.getInstance().goldcoindepot.get(key, value);
	}

	public static void insert(Long key, Long value) {
		_Tables_.getInstance().goldcoindepot.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().goldcoindepot.delete(key);
	}

	public static boolean add(Long key, Long value) {
		return _Tables_.getInstance().goldcoindepot.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().goldcoindepot.remove(key);
	}

	public static xdb.TTableCache<Long, Long> getCache() {
		return _Tables_.getInstance().goldcoindepot.getCache();
	}

	public static xdb.TTable<Long, Long> getTable() {
		return _Tables_.getInstance().goldcoindepot;
	}

	public static Long select(Long key) {
		return getTable().select(key, new xdb.TField<Long, Long>() {
			public Long get(Long v) { return v; }
		});
	}

}
