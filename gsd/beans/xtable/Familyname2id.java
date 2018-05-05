package xtable;

// typed table access point
public class Familyname2id {
	Familyname2id() {
	}

	public static xbean.FNameToID get(Integer key) {
		return _Tables_.getInstance().familyname2id.get(key);
	}

	public static xbean.FNameToID get(Integer key, xbean.FNameToID value) {
		return _Tables_.getInstance().familyname2id.get(key, value);
	}

	public static void insert(Integer key, xbean.FNameToID value) {
		_Tables_.getInstance().familyname2id.insert(key, value);
	}

	public static void delete(Integer key) {
		_Tables_.getInstance().familyname2id.delete(key);
	}

	public static boolean add(Integer key, xbean.FNameToID value) {
		return _Tables_.getInstance().familyname2id.add(key, value);
	}

	public static boolean remove(Integer key) {
		return _Tables_.getInstance().familyname2id.remove(key);
	}

	public static xdb.TTableCache<Integer, xbean.FNameToID> getCache() {
		return _Tables_.getInstance().familyname2id.getCache();
	}

	public static xdb.TTable<Integer, xbean.FNameToID> getTable() {
		return _Tables_.getInstance().familyname2id;
	}

	public static xbean.FNameToID select(Integer key) {
		return getTable().select(key, new xdb.TField<xbean.FNameToID, xbean.FNameToID>() {
			public xbean.FNameToID get(xbean.FNameToID v) { return v.toData(); }
		});
	}

	public static java.util.Map<String, Long> selectData(Integer key) {
		return getTable().select(key, new xdb.TField<xbean.FNameToID, java.util.Map<String, Long>>() {
				public java.util.Map<String, Long> get(xbean.FNameToID v) { return v.getDataAsData(); }
			});
	}

}
