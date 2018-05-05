package xtable;

// typed table access point
public class Family {
	Family() {
	}

	public static xdb.util.AutoKey<Long> getAutoKey() {
		return _Tables_.getInstance().family.getAutoKey();
	}

	public static Long nextKey() {
		return getAutoKey().next();
	}

	public static Long insert(xbean.Family value) {
		Long next = nextKey();
		insert(next, value);
		return next;
	}

	public static xbean.Family get(Long key) {
		return _Tables_.getInstance().family.get(key);
	}

	public static xbean.Family get(Long key, xbean.Family value) {
		return _Tables_.getInstance().family.get(key, value);
	}

	public static void insert(Long key, xbean.Family value) {
		_Tables_.getInstance().family.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().family.delete(key);
	}

	public static boolean add(Long key, xbean.Family value) {
		return _Tables_.getInstance().family.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().family.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.Family> getCache() {
		return _Tables_.getInstance().family.getCache();
	}

	public static xdb.TTable<Long, xbean.Family> getTable() {
		return _Tables_.getInstance().family;
	}

	public static xbean.Family select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.Family, xbean.Family>() {
			public xbean.Family get(xbean.Family v) { return v.toData(); }
		});
	}

	public static Long selectFamilyid(Long key) {
		return getTable().select(key, new xdb.TField<xbean.Family, Long>() {
				public Long get(xbean.Family v) { return v.getFamilyid(); }
			});
	}

	public static String selectFamilyname(Long key) {
		return getTable().select(key, new xdb.TField<xbean.Family, String>() {
				public String get(xbean.Family v) { return v.getFamilyname(); }
			});
	}

	public static Integer selectFlevel(Long key) {
		return getTable().select(key, new xdb.TField<xbean.Family, Integer>() {
				public Integer get(xbean.Family v) { return v.getFlevel(); }
			});
	}

	public static Long selectMoney(Long key) {
		return getTable().select(key, new xdb.TField<xbean.Family, Long>() {
				public Long get(xbean.Family v) { return v.getMoney(); }
			});
	}

	public static Integer selectCurlvlbuilddegree(Long key) {
		return getTable().select(key, new xdb.TField<xbean.Family, Integer>() {
				public Integer get(xbean.Family v) { return v.getCurlvlbuilddegree(); }
			});
	}

	public static Integer selectTotalbuilddegree(Long key) {
		return getTable().select(key, new xdb.TField<xbean.Family, Integer>() {
				public Integer get(xbean.Family v) { return v.getTotalbuilddegree(); }
			});
	}

	public static Long selectTotalbanggong(Long key) {
		return getTable().select(key, new xdb.TField<xbean.Family, Long>() {
				public Long get(xbean.Family v) { return v.getTotalbanggong(); }
			});
	}

	public static String selectDeclaration(Long key) {
		return getTable().select(key, new xdb.TField<xbean.Family, String>() {
				public String get(xbean.Family v) { return v.getDeclaration(); }
			});
	}

	public static String selectPublicinfo(Long key) {
		return getTable().select(key, new xdb.TField<xbean.Family, String>() {
				public String get(xbean.Family v) { return v.getPublicinfo(); }
			});
	}

	public static Long selectPublictime(Long key) {
		return getTable().select(key, new xdb.TField<xbean.Family, Long>() {
				public Long get(xbean.Family v) { return v.getPublictime(); }
			});
	}

	public static Integer selectMalllevel(Long key) {
		return getTable().select(key, new xdb.TField<xbean.Family, Integer>() {
				public Integer get(xbean.Family v) { return v.getMalllevel(); }
			});
	}

	public static java.util.List<xbean.FamilyLogReport> selectLogs(Long key) {
		return getTable().select(key, new xdb.TField<xbean.Family, java.util.List<xbean.FamilyLogReport>>() {
				public java.util.List<xbean.FamilyLogReport> get(xbean.Family v) { return v.getLogsAsData(); }
			});
	}

	public static java.util.Map<Long, Long> selectRequestinglist(Long key) {
		return getTable().select(key, new xdb.TField<xbean.Family, java.util.Map<Long, Long>>() {
				public java.util.Map<Long, Long> get(xbean.Family v) { return v.getRequestinglistAsData(); }
			});
	}

	public static xbean.FamilyActivity selectActivity(Long key) {
		return getTable().select(key, new xdb.TField<xbean.Family, xbean.FamilyActivity>() {
				public xbean.FamilyActivity get(xbean.Family v) { return v.getActivity(); }
			});
	}

	public static xbean.FamilyGodAnimalAct selectBeatanimalactivity(Long key) {
		return getTable().select(key, new xdb.TField<xbean.Family, xbean.FamilyGodAnimalAct>() {
				public xbean.FamilyGodAnimalAct get(xbean.Family v) { return v.getBeatanimalactivity(); }
			});
	}

	public static xbean.FamilyWelfare selectWelfare(Long key) {
		return getTable().select(key, new xdb.TField<xbean.Family, xbean.FamilyWelfare>() {
				public xbean.FamilyWelfare get(xbean.Family v) { return v.getWelfare(); }
			});
	}

	public static Long selectChiefid(Long key) {
		return getTable().select(key, new xdb.TField<xbean.Family, Long>() {
				public Long get(xbean.Family v) { return v.getChiefid(); }
			});
	}

	public static java.util.Map<Integer, xbean.FamilyJobStaffList> selectJobinfo(Long key) {
		return getTable().select(key, new xdb.TField<xbean.Family, java.util.Map<Integer, xbean.FamilyJobStaffList>>() {
				public java.util.Map<Integer, xbean.FamilyJobStaffList> get(xbean.Family v) { return v.getJobinfoAsData(); }
			});
	}

	public static java.util.Map<Long, xbean.FamilyMember> selectMembers(Long key) {
		return getTable().select(key, new xdb.TField<xbean.Family, java.util.Map<Long, xbean.FamilyMember>>() {
				public java.util.Map<Long, xbean.FamilyMember> get(xbean.Family v) { return v.getMembersAsData(); }
			});
	}

	public static Long selectUpdatetime(Long key) {
		return getTable().select(key, new xdb.TField<xbean.Family, Long>() {
				public Long get(xbean.Family v) { return v.getUpdatetime(); }
			});
	}

	public static Long selectCreatetime(Long key) {
		return getTable().select(key, new xdb.TField<xbean.Family, Long>() {
				public Long get(xbean.Family v) { return v.getCreatetime(); }
			});
	}

	public static Long selectLastpartyopentime(Long key) {
		return getTable().select(key, new xdb.TField<xbean.Family, Long>() {
				public Long get(xbean.Family v) { return v.getLastpartyopentime(); }
			});
	}

	public static Long selectLastpartycalltime(Long key) {
		return getTable().select(key, new xdb.TField<xbean.Family, Long>() {
				public Long get(xbean.Family v) { return v.getLastpartycalltime(); }
			});
	}

	public static Integer selectIssystemfam(Long key) {
		return getTable().select(key, new xdb.TField<xbean.Family, Integer>() {
				public Integer get(xbean.Family v) { return v.getIssystemfam(); }
			});
	}

	public static java.util.Map<Long, Long> selectInvitelist(Long key) {
		return getTable().select(key, new xdb.TField<xbean.Family, java.util.Map<Long, Long>>() {
				public java.util.Map<Long, Long> get(xbean.Family v) { return v.getInvitelistAsData(); }
			});
	}

	public static Long selectLastresettime(Long key) {
		return getTable().select(key, new xdb.TField<xbean.Family, Long>() {
				public Long get(xbean.Family v) { return v.getLastresettime(); }
			});
	}

}
