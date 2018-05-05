package xtable;

// typed table access point
public class Rolewelfareinfo {
	Rolewelfareinfo() {
	}

	public static xbean.RoleWelfareInfo get(Long key) {
		return _Tables_.getInstance().rolewelfareinfo.get(key);
	}

	public static xbean.RoleWelfareInfo get(Long key, xbean.RoleWelfareInfo value) {
		return _Tables_.getInstance().rolewelfareinfo.get(key, value);
	}

	public static void insert(Long key, xbean.RoleWelfareInfo value) {
		_Tables_.getInstance().rolewelfareinfo.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().rolewelfareinfo.delete(key);
	}

	public static boolean add(Long key, xbean.RoleWelfareInfo value) {
		return _Tables_.getInstance().rolewelfareinfo.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().rolewelfareinfo.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.RoleWelfareInfo> getCache() {
		return _Tables_.getInstance().rolewelfareinfo.getCache();
	}

	public static xdb.TTable<Long, xbean.RoleWelfareInfo> getTable() {
		return _Tables_.getInstance().rolewelfareinfo;
	}

	public static xbean.RoleWelfareInfo select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleWelfareInfo, xbean.RoleWelfareInfo>() {
			public xbean.RoleWelfareInfo get(xbean.RoleWelfareInfo v) { return v.toData(); }
		});
	}

	public static Long selectLastsigntime(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleWelfareInfo, Long>() {
				public Long get(xbean.RoleWelfareInfo v) { return v.getLastsigntime(); }
			});
	}

	public static Integer selectTotalsigncount(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleWelfareInfo, Integer>() {
				public Integer get(xbean.RoleWelfareInfo v) { return v.getTotalsigncount(); }
			});
	}

	public static Integer selectContinuesigncount(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleWelfareInfo, Integer>() {
				public Integer get(xbean.RoleWelfareInfo v) { return v.getContinuesigncount(); }
			});
	}

	public static java.util.List<Integer> selectSigndate(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleWelfareInfo, java.util.List<Integer>>() {
				public java.util.List<Integer> get(xbean.RoleWelfareInfo v) { return v.getSigndateAsData(); }
			});
	}

	public static Integer selectHastodaysign(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleWelfareInfo, Integer>() {
				public Integer get(xbean.RoleWelfareInfo v) { return v.getHastodaysign(); }
			});
	}

	public static java.util.List<Integer> selectReceivedmonthcarddate(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleWelfareInfo, java.util.List<Integer>>() {
				public java.util.List<Integer> get(xbean.RoleWelfareInfo v) { return v.getReceivedmonthcarddateAsData(); }
			});
	}

	public static java.util.List<Integer> selectReceivedonlinegift(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleWelfareInfo, java.util.List<Integer>>() {
				public java.util.List<Integer> get(xbean.RoleWelfareInfo v) { return v.getReceivedonlinegiftAsData(); }
			});
	}

	public static java.util.List<Integer> selectReceivednewgift(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleWelfareInfo, java.util.List<Integer>>() {
				public java.util.List<Integer> get(xbean.RoleWelfareInfo v) { return v.getReceivednewgiftAsData(); }
			});
	}

	public static java.util.List<Integer> selectReceiveconlogingift(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleWelfareInfo, java.util.List<Integer>>() {
				public java.util.List<Integer> get(xbean.RoleWelfareInfo v) { return v.getReceiveconlogingiftAsData(); }
			});
	}

	public static Integer selectWishtimes(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleWelfareInfo, Integer>() {
				public Integer get(xbean.RoleWelfareInfo v) { return v.getWishtimes(); }
			});
	}

	public static Long selectLastwishtime(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleWelfareInfo, Long>() {
				public Long get(xbean.RoleWelfareInfo v) { return v.getLastwishtime(); }
			});
	}

	public static Long selectLastwishpetid(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleWelfareInfo, Long>() {
				public Long get(xbean.RoleWelfareInfo v) { return v.getLastwishpetid(); }
			});
	}

	public static java.util.List<Integer> selectReceivedgrowplangift(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleWelfareInfo, java.util.List<Integer>>() {
				public java.util.List<Integer> get(xbean.RoleWelfareInfo v) { return v.getReceivedgrowplangiftAsData(); }
			});
	}

	public static java.util.List<Integer> selectReceivedpaycharge(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleWelfareInfo, java.util.List<Integer>>() {
				public java.util.List<Integer> get(xbean.RoleWelfareInfo v) { return v.getReceivedpaychargeAsData(); }
			});
	}

	public static Integer selectIseatlunch(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleWelfareInfo, Integer>() {
				public Integer get(xbean.RoleWelfareInfo v) { return v.getIseatlunch(); }
			});
	}

	public static Integer selectIseatdinner(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleWelfareInfo, Integer>() {
				public Integer get(xbean.RoleWelfareInfo v) { return v.getIseatdinner(); }
			});
	}

	public static java.util.List<Integer> selectThreegrowplan(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleWelfareInfo, java.util.List<Integer>>() {
				public java.util.List<Integer> get(xbean.RoleWelfareInfo v) { return v.getThreegrowplanAsData(); }
			});
	}

	public static java.util.List<Integer> selectFivegrowplan(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleWelfareInfo, java.util.List<Integer>>() {
				public java.util.List<Integer> get(xbean.RoleWelfareInfo v) { return v.getFivegrowplanAsData(); }
			});
	}

	public static java.util.List<Integer> selectSevengrowplan(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleWelfareInfo, java.util.List<Integer>>() {
				public java.util.List<Integer> get(xbean.RoleWelfareInfo v) { return v.getSevengrowplanAsData(); }
			});
	}

}
