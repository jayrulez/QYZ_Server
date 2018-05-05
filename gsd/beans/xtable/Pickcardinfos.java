package xtable;

// typed table access point
public class Pickcardinfos {
	Pickcardinfos() {
	}

	public static xbean.PickCardInfo get(Long key) {
		return _Tables_.getInstance().pickcardinfos.get(key);
	}

	public static xbean.PickCardInfo get(Long key, xbean.PickCardInfo value) {
		return _Tables_.getInstance().pickcardinfos.get(key, value);
	}

	public static void insert(Long key, xbean.PickCardInfo value) {
		_Tables_.getInstance().pickcardinfos.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().pickcardinfos.delete(key);
	}

	public static boolean add(Long key, xbean.PickCardInfo value) {
		return _Tables_.getInstance().pickcardinfos.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().pickcardinfos.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.PickCardInfo> getCache() {
		return _Tables_.getInstance().pickcardinfos.getCache();
	}

	public static xdb.TTable<Long, xbean.PickCardInfo> getTable() {
		return _Tables_.getInstance().pickcardinfos;
	}

	public static xbean.PickCardInfo select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.PickCardInfo, xbean.PickCardInfo>() {
			public xbean.PickCardInfo get(xbean.PickCardInfo v) { return v.toData(); }
		});
	}

	public static Long selectLastfreehuoban(Long key) {
		return getTable().select(key, new xdb.TField<xbean.PickCardInfo, Long>() {
				public Long get(xbean.PickCardInfo v) { return v.getLastfreehuoban(); }
			});
	}

	public static Integer selectHuobanhighyuanbao(Long key) {
		return getTable().select(key, new xdb.TField<xbean.PickCardInfo, Integer>() {
				public Integer get(xbean.PickCardInfo v) { return v.getHuobanhighyuanbao(); }
			});
	}

	public static Integer selectHuobanlowyuanbao(Long key) {
		return getTable().select(key, new xdb.TField<xbean.PickCardInfo, Integer>() {
				public Integer get(xbean.PickCardInfo v) { return v.getHuobanlowyuanbao(); }
			});
	}

	public static Integer selectFabaoyuanbao(Long key) {
		return getTable().select(key, new xdb.TField<xbean.PickCardInfo, Integer>() {
				public Integer get(xbean.PickCardInfo v) { return v.getFabaoyuanbao(); }
			});
	}

	public static Integer selectFabaofree(Long key) {
		return getTable().select(key, new xdb.TField<xbean.PickCardInfo, Integer>() {
				public Integer get(xbean.PickCardInfo v) { return v.getFabaofree(); }
			});
	}

	public static Integer selectFabaoxunibi(Long key) {
		return getTable().select(key, new xdb.TField<xbean.PickCardInfo, Integer>() {
				public Integer get(xbean.PickCardInfo v) { return v.getFabaoxunibi(); }
			});
	}

}
