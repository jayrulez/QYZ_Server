package xtable;

// typed table access point
public class Huiwucurterms {
	Huiwucurterms() {
	}

	public static xbean.HuiWuCurTerm get(Integer key) {
		return _Tables_.getInstance().huiwucurterms.get(key);
	}

	public static xbean.HuiWuCurTerm get(Integer key, xbean.HuiWuCurTerm value) {
		return _Tables_.getInstance().huiwucurterms.get(key, value);
	}

	public static void insert(Integer key, xbean.HuiWuCurTerm value) {
		_Tables_.getInstance().huiwucurterms.insert(key, value);
	}

	public static void delete(Integer key) {
		_Tables_.getInstance().huiwucurterms.delete(key);
	}

	public static boolean add(Integer key, xbean.HuiWuCurTerm value) {
		return _Tables_.getInstance().huiwucurterms.add(key, value);
	}

	public static boolean remove(Integer key) {
		return _Tables_.getInstance().huiwucurterms.remove(key);
	}

	public static xdb.TTableCache<Integer, xbean.HuiWuCurTerm> getCache() {
		return _Tables_.getInstance().huiwucurterms.getCache();
	}

	public static xdb.TTable<Integer, xbean.HuiWuCurTerm> getTable() {
		return _Tables_.getInstance().huiwucurterms;
	}

	public static xbean.HuiWuCurTerm select(Integer key) {
		return getTable().select(key, new xdb.TField<xbean.HuiWuCurTerm, xbean.HuiWuCurTerm>() {
			public xbean.HuiWuCurTerm get(xbean.HuiWuCurTerm v) { return v.toData(); }
		});
	}

	public static Integer selectTermid(Integer key) {
		return getTable().select(key, new xdb.TField<xbean.HuiWuCurTerm, Integer>() {
				public Integer get(xbean.HuiWuCurTerm v) { return v.getTermid(); }
			});
	}

	public static Long selectOpentime(Integer key) {
		return getTable().select(key, new xdb.TField<xbean.HuiWuCurTerm, Long>() {
				public Long get(xbean.HuiWuCurTerm v) { return v.getOpentime(); }
			});
	}

	public static Long selectEndtime(Integer key) {
		return getTable().select(key, new xdb.TField<xbean.HuiWuCurTerm, Long>() {
				public Long get(xbean.HuiWuCurTerm v) { return v.getEndtime(); }
			});
	}

	public static Long selectGuessendtime(Integer key) {
		return getTable().select(key, new xdb.TField<xbean.HuiWuCurTerm, Long>() {
				public Long get(xbean.HuiWuCurTerm v) { return v.getGuessendtime(); }
			});
	}

	public static Integer selectStage(Integer key) {
		return getTable().select(key, new xdb.TField<xbean.HuiWuCurTerm, Integer>() {
				public Integer get(xbean.HuiWuCurTerm v) { return v.getStage(); }
			});
	}

	public static Integer selectRoundindex(Integer key) {
		return getTable().select(key, new xdb.TField<xbean.HuiWuCurTerm, Integer>() {
				public Integer get(xbean.HuiWuCurTerm v) { return v.getRoundindex(); }
			});
	}

	public static Integer selectRoundstage(Integer key) {
		return getTable().select(key, new xdb.TField<xbean.HuiWuCurTerm, Integer>() {
				public Integer get(xbean.HuiWuCurTerm v) { return v.getRoundstage(); }
			});
	}

	public static Long selectBattlebegintime(Integer key) {
		return getTable().select(key, new xdb.TField<xbean.HuiWuCurTerm, Long>() {
				public Long get(xbean.HuiWuCurTerm v) { return v.getBattlebegintime(); }
			});
	}

	public static java.util.Map<Integer, xbean.HuiWuProfessionTerm> selectTerminfobyprofession(Integer key) {
		return getTable().select(key, new xdb.TField<xbean.HuiWuCurTerm, java.util.Map<Integer, xbean.HuiWuProfessionTerm>>() {
				public java.util.Map<Integer, xbean.HuiWuProfessionTerm> get(xbean.HuiWuCurTerm v) { return v.getTerminfobyprofessionAsData(); }
			});
	}

	public static java.util.Map<Long, Long> selectGuess(Integer key) {
		return getTable().select(key, new xdb.TField<xbean.HuiWuCurTerm, java.util.Map<Long, Long>>() {
				public java.util.Map<Long, Long> get(xbean.HuiWuCurTerm v) { return v.getGuessAsData(); }
			});
	}

	public static java.util.Map<Long, Integer> selectContinuouschampions(Integer key) {
		return getTable().select(key, new xdb.TField<xbean.HuiWuCurTerm, java.util.Map<Long, Integer>>() {
				public java.util.Map<Long, Integer> get(xbean.HuiWuCurTerm v) { return v.getContinuouschampionsAsData(); }
			});
	}

}
