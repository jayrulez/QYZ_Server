package xtable;

// typed table access point
public class Systemmailbox {
	Systemmailbox() {
	}

	public static xbean.SystemMail get(Long key) {
		return _Tables_.getInstance().systemmailbox.get(key);
	}

	public static xbean.SystemMail get(Long key, xbean.SystemMail value) {
		return _Tables_.getInstance().systemmailbox.get(key, value);
	}

	public static void insert(Long key, xbean.SystemMail value) {
		_Tables_.getInstance().systemmailbox.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().systemmailbox.delete(key);
	}

	public static boolean add(Long key, xbean.SystemMail value) {
		return _Tables_.getInstance().systemmailbox.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().systemmailbox.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.SystemMail> getCache() {
		return _Tables_.getInstance().systemmailbox.getCache();
	}

	public static xdb.TTable<Long, xbean.SystemMail> getTable() {
		return _Tables_.getInstance().systemmailbox;
	}

	public static xbean.SystemMail select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.SystemMail, xbean.SystemMail>() {
			public xbean.SystemMail get(xbean.SystemMail v) { return v.toData(); }
		});
	}

	public static Long selectId(Long key) {
		return getTable().select(key, new xdb.TField<xbean.SystemMail, Long>() {
				public Long get(xbean.SystemMail v) { return v.getId(); }
			});
	}

	public static Integer selectMailid(Long key) {
		return getTable().select(key, new xdb.TField<xbean.SystemMail, Integer>() {
				public Integer get(xbean.SystemMail v) { return v.getMailid(); }
			});
	}

	public static Long selectSendtime(Long key) {
		return getTable().select(key, new xdb.TField<xbean.SystemMail, Long>() {
				public Long get(xbean.SystemMail v) { return v.getSendtime(); }
			});
	}

	public static String selectTitle(Long key) {
		return getTable().select(key, new xdb.TField<xbean.SystemMail, String>() {
				public String get(xbean.SystemMail v) { return v.getTitle(); }
			});
	}

	public static String selectContent(Long key) {
		return getTable().select(key, new xdb.TField<xbean.SystemMail, String>() {
				public String get(xbean.SystemMail v) { return v.getContent(); }
			});
	}

	public static xbean.Bonus selectBonus(Long key) {
		return getTable().select(key, new xdb.TField<xbean.SystemMail, xbean.Bonus>() {
				public xbean.Bonus get(xbean.SystemMail v) { return v.getBonus(); }
			});
	}

	public static java.util.List<String> selectParams(Long key) {
		return getTable().select(key, new xdb.TField<xbean.SystemMail, java.util.List<String>>() {
				public java.util.List<String> get(xbean.SystemMail v) { return v.getParamsAsData(); }
			});
	}

	public static java.util.Set<Long> selectRecords(Long key) {
		return getTable().select(key, new xdb.TField<xbean.SystemMail, java.util.Set<Long>>() {
				public java.util.Set<Long> get(xbean.SystemMail v) { return v.getRecordsAsData(); }
			});
	}

}
