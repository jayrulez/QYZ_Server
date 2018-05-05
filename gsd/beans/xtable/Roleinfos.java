package xtable;

// typed table access point
public class Roleinfos {
	Roleinfos() {
	}

	public static xdb.util.AutoKey<Long> getAutoKey() {
		return _Tables_.getInstance().roleinfos.getAutoKey();
	}

	public static Long nextKey() {
		return getAutoKey().next();
	}

	public static Long insert(xbean.RoleInfo value) {
		Long next = nextKey();
		insert(next, value);
		return next;
	}

	public static xbean.RoleInfo get(Long key) {
		return _Tables_.getInstance().roleinfos.get(key);
	}

	public static xbean.RoleInfo get(Long key, xbean.RoleInfo value) {
		return _Tables_.getInstance().roleinfos.get(key, value);
	}

	public static void insert(Long key, xbean.RoleInfo value) {
		_Tables_.getInstance().roleinfos.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().roleinfos.delete(key);
	}

	public static boolean add(Long key, xbean.RoleInfo value) {
		return _Tables_.getInstance().roleinfos.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().roleinfos.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.RoleInfo> getCache() {
		return _Tables_.getInstance().roleinfos.getCache();
	}

	public static xdb.TTable<Long, xbean.RoleInfo> getTable() {
		return _Tables_.getInstance().roleinfos;
	}

	public static xbean.RoleInfo select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleInfo, xbean.RoleInfo>() {
			public xbean.RoleInfo get(xbean.RoleInfo v) { return v.toData(); }
		});
	}

	public static Long selectUserid(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleInfo, Long>() {
				public Long get(xbean.RoleInfo v) { return v.getUserid(); }
			});
	}

	public static Integer selectProfession(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleInfo, Integer>() {
				public Integer get(xbean.RoleInfo v) { return v.getProfession(); }
			});
	}

	public static Long selectCreatetime(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleInfo, Long>() {
				public Long get(xbean.RoleInfo v) { return v.getCreatetime(); }
			});
	}

	public static Integer selectServerid(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleInfo, Integer>() {
				public Integer get(xbean.RoleInfo v) { return v.getServerid(); }
			});
	}

	public static Long selectLastlogintime(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleInfo, Long>() {
				public Long get(xbean.RoleInfo v) { return v.getLastlogintime(); }
			});
	}

	public static Long selectTotalonlinetime(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleInfo, Long>() {
				public Long get(xbean.RoleInfo v) { return v.getTotalonlinetime(); }
			});
	}

	public static Long selectLastlvluptotalonlinetime(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleInfo, Long>() {
				public Long get(xbean.RoleInfo v) { return v.getLastlvluptotalonlinetime(); }
			});
	}

	public static Long selectLastlogouttime(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleInfo, Long>() {
				public Long get(xbean.RoleInfo v) { return v.getLastlogouttime(); }
			});
	}

	public static Integer selectLogindaycount(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleInfo, Integer>() {
				public Integer get(xbean.RoleInfo v) { return v.getLogindaycount(); }
			});
	}

	public static Long selectLastaddlogindaytime(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleInfo, Long>() {
				public Long get(xbean.RoleInfo v) { return v.getLastaddlogindaytime(); }
			});
	}

	public static Integer selectContinuesloginday(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleInfo, Integer>() {
				public Integer get(xbean.RoleInfo v) { return v.getContinuesloginday(); }
			});
	}

	public static Long selectTotaldayonlinetime(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleInfo, Long>() {
				public Long get(xbean.RoleInfo v) { return v.getTotaldayonlinetime(); }
			});
	}

	public static Integer selectLeftgifttimes(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleInfo, Integer>() {
				public Integer get(xbean.RoleInfo v) { return v.getLeftgifttimes(); }
			});
	}

	public static Integer selectMonthcardlefttimes(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleInfo, Integer>() {
				public Integer get(xbean.RoleInfo v) { return v.getMonthcardlefttimes(); }
			});
	}

	public static Integer selectBuymonthcardtimes(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleInfo, Integer>() {
				public Integer get(xbean.RoleInfo v) { return v.getBuymonthcardtimes(); }
			});
	}

	public static Integer selectBuygrowplan(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleInfo, Integer>() {
				public Integer get(xbean.RoleInfo v) { return v.getBuygrowplan(); }
			});
	}

	public static Integer selectGrowonetime(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleInfo, Integer>() {
				public Integer get(xbean.RoleInfo v) { return v.getGrowonetime(); }
			});
	}

	public static Integer selectBuygrowplantwo(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleInfo, Integer>() {
				public Integer get(xbean.RoleInfo v) { return v.getBuygrowplantwo(); }
			});
	}

	public static Integer selectGrowtwotime(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleInfo, Integer>() {
				public Integer get(xbean.RoleInfo v) { return v.getGrowtwotime(); }
			});
	}

	public static Integer selectBuygrowplanthree(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleInfo, Integer>() {
				public Integer get(xbean.RoleInfo v) { return v.getBuygrowplanthree(); }
			});
	}

	public static Integer selectGrowthreetime(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleInfo, Integer>() {
				public Integer get(xbean.RoleInfo v) { return v.getGrowthreetime(); }
			});
	}

	public static String selectName(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleInfo, String>() {
				public String get(xbean.RoleInfo v) { return v.getName(); }
			});
	}

	public static Integer selectChangenametime(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleInfo, Integer>() {
				public Integer get(xbean.RoleInfo v) { return v.getChangenametime(); }
			});
	}

	public static Integer selectLevel(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleInfo, Integer>() {
				public Integer get(xbean.RoleInfo v) { return v.getLevel(); }
			});
	}

	public static Long selectLastlvluptime(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleInfo, Long>() {
				public Long get(xbean.RoleInfo v) { return v.getLastlvluptime(); }
			});
	}

	public static Integer selectViplevel(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleInfo, Integer>() {
				public Integer get(xbean.RoleInfo v) { return v.getViplevel(); }
			});
	}

	public static Long selectVipexp(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleInfo, Long>() {
				public Long get(xbean.RoleInfo v) { return v.getVipexp(); }
			});
	}

	public static java.util.Map<Integer, Long> selectCurrencys(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleInfo, java.util.Map<Integer, Long>>() {
				public java.util.Map<Integer, Long> get(xbean.RoleInfo v) { return v.getCurrencysAsData(); }
			});
	}

	public static Integer selectGender(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleInfo, Integer>() {
				public Integer get(xbean.RoleInfo v) { return v.getGender(); }
			});
	}

	public static Integer selectTotalcostyuanbao(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleInfo, Integer>() {
				public Integer get(xbean.RoleInfo v) { return v.getTotalcostyuanbao(); }
			});
	}

	public static Long selectLastaddtilitime(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleInfo, Long>() {
				public Long get(xbean.RoleInfo v) { return v.getLastaddtilitime(); }
			});
	}

	public static Long selectLastworldtalktime(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleInfo, Long>() {
				public Long get(xbean.RoleInfo v) { return v.getLastworldtalktime(); }
			});
	}

	public static Long selectSilentendtime(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleInfo, Long>() {
				public Long get(xbean.RoleInfo v) { return v.getSilentendtime(); }
			});
	}

	public static Integer selectLeftreporttime(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleInfo, Integer>() {
				public Integer get(xbean.RoleInfo v) { return v.getLeftreporttime(); }
			});
	}

	public static Integer selectBereportedtime(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleInfo, Integer>() {
				public Integer get(xbean.RoleInfo v) { return v.getBereportedtime(); }
			});
	}

}
