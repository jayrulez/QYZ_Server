package xtable;

// typed table access point
public class Idgen {
	Idgen() {
	}

	public static xbean.IdGen get(Long key) {
		return _Tables_.getInstance().idgen.get(key);
	}

	public static xbean.IdGen get(Long key, xbean.IdGen value) {
		return _Tables_.getInstance().idgen.get(key, value);
	}

	public static void insert(Long key, xbean.IdGen value) {
		_Tables_.getInstance().idgen.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().idgen.delete(key);
	}

	public static boolean add(Long key, xbean.IdGen value) {
		return _Tables_.getInstance().idgen.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().idgen.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.IdGen> getCache() {
		return _Tables_.getInstance().idgen.getCache();
	}

	public static xdb.TTable<Long, xbean.IdGen> getTable() {
		return _Tables_.getInstance().idgen;
	}

	public static xbean.IdGen select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.IdGen, xbean.IdGen>() {
			public xbean.IdGen get(xbean.IdGen v) { return v.toData(); }
		});
	}

	public static Long selectItemid(Long key) {
		return getTable().select(key, new xdb.TField<xbean.IdGen, Long>() {
				public Long get(xbean.IdGen v) { return v.getItemid(); }
			});
	}

}
