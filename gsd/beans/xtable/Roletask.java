package xtable;

// typed table access point
public class Roletask {
	Roletask() {
	}

	public static xbean.RoleTask get(Long key) {
		return _Tables_.getInstance().roletask.get(key);
	}

	public static xbean.RoleTask get(Long key, xbean.RoleTask value) {
		return _Tables_.getInstance().roletask.get(key, value);
	}

	public static void insert(Long key, xbean.RoleTask value) {
		_Tables_.getInstance().roletask.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().roletask.delete(key);
	}

	public static boolean add(Long key, xbean.RoleTask value) {
		return _Tables_.getInstance().roletask.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().roletask.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.RoleTask> getCache() {
		return _Tables_.getInstance().roletask.getCache();
	}

	public static xdb.TTable<Long, xbean.RoleTask> getTable() {
		return _Tables_.getInstance().roletask;
	}

	public static xbean.RoleTask select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleTask, xbean.RoleTask>() {
			public xbean.RoleTask get(xbean.RoleTask v) { return v.toData(); }
		});
	}

	public static Long selectRoleid(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleTask, Long>() {
				public Long get(xbean.RoleTask v) { return v.getRoleid(); }
			});
	}

	public static Long selectMinhistoryexpiretime(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleTask, Long>() {
				public Long get(xbean.RoleTask v) { return v.getMinhistoryexpiretime(); }
			});
	}

	public static java.util.Map<Integer, xbean.TaskHistory> selectHistorys(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleTask, java.util.Map<Integer, xbean.TaskHistory>>() {
				public java.util.Map<Integer, xbean.TaskHistory> get(xbean.RoleTask v) { return v.getHistorysAsData(); }
			});
	}

	public static java.util.Map<Integer, Integer> selectLastcompletetaskid(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleTask, java.util.Map<Integer, Integer>>() {
				public java.util.Map<Integer, Integer> get(xbean.RoleTask v) { return v.getLastcompletetaskidAsData(); }
			});
	}

	public static Integer selectAcceptbranchtasks(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleTask, Integer>() {
				public Integer get(xbean.RoleTask v) { return v.getAcceptbranchtasks(); }
			});
	}

	public static java.util.Set<Integer> selectShownpcs(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleTask, java.util.Set<Integer>>() {
				public java.util.Set<Integer> get(xbean.RoleTask v) { return v.getShownpcsAsData(); }
			});
	}

	public static java.util.Set<Integer> selectHidemines(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleTask, java.util.Set<Integer>>() {
				public java.util.Set<Integer> get(xbean.RoleTask v) { return v.getHideminesAsData(); }
			});
	}

	public static java.util.Map<Integer, xbean.TaskData> selectTasks(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleTask, java.util.Map<Integer, xbean.TaskData>>() {
				public java.util.Map<Integer, xbean.TaskData> get(xbean.RoleTask v) { return v.getTasksAsData(); }
			});
	}

	public static java.util.Map<Integer, Integer> selectVariables(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleTask, java.util.Map<Integer, Integer>>() {
				public java.util.Map<Integer, Integer> get(xbean.RoleTask v) { return v.getVariablesAsData(); }
			});
	}

	public static java.util.List<xbean.FamilyTaskDetail> selectAcceptedfamilytasks(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleTask, java.util.List<xbean.FamilyTaskDetail>>() {
				public java.util.List<xbean.FamilyTaskDetail> get(xbean.RoleTask v) { return v.getAcceptedfamilytasksAsData(); }
			});
	}

	public static Integer selectCompletednumsinfamtasks(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleTask, Integer>() {
				public Integer get(xbean.RoleTask v) { return v.getCompletednumsinfamtasks(); }
			});
	}

	public static Integer selectIsuseyuanbao(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleTask, Integer>() {
				public Integer get(xbean.RoleTask v) { return v.getIsuseyuanbao(); }
			});
	}

	public static Integer selectIscancletask(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleTask, Integer>() {
				public Integer get(xbean.RoleTask v) { return v.getIscancletask(); }
			});
	}

	public static Integer selectDailycompletedfamtasks(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleTask, Integer>() {
				public Integer get(xbean.RoleTask v) { return v.getDailycompletedfamtasks(); }
			});
	}

	public static Integer selectWeekcompletedsmallfamtasks(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleTask, Integer>() {
				public Integer get(xbean.RoleTask v) { return v.getWeekcompletedsmallfamtasks(); }
			});
	}

	public static java.util.List<Integer> selectWeekspebonus(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleTask, java.util.List<Integer>>() {
				public java.util.List<Integer> get(xbean.RoleTask v) { return v.getWeekspebonusAsData(); }
			});
	}

	public static Long selectLastgiveupfamtasktime(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleTask, Long>() {
				public Long get(xbean.RoleTask v) { return v.getLastgiveupfamtasktime(); }
			});
	}

	public static Integer selectGuidebranchtaskid(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleTask, Integer>() {
				public Integer get(xbean.RoleTask v) { return v.getGuidebranchtaskid(); }
			});
	}

	public static java.util.Map<Integer, Long> selectAccepttasktime(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleTask, java.util.Map<Integer, Long>>() {
				public java.util.Map<Integer, Long> get(xbean.RoleTask v) { return v.getAccepttasktimeAsData(); }
			});
	}

	public static java.util.Map<Integer, Integer> selectFamilytaskorder(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleTask, java.util.Map<Integer, Integer>>() {
				public java.util.Map<Integer, Integer> get(xbean.RoleTask v) { return v.getFamilytaskorderAsData(); }
			});
	}

	public static java.util.Map<Integer, Integer> selectFamilylasttaskorder(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleTask, java.util.Map<Integer, Integer>>() {
				public java.util.Map<Integer, Integer> get(xbean.RoleTask v) { return v.getFamilylasttaskorderAsData(); }
			});
	}

	public static java.util.Set<Integer> selectAllcandobranch(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleTask, java.util.Set<Integer>>() {
				public java.util.Set<Integer> get(xbean.RoleTask v) { return v.getAllcandobranchAsData(); }
			});
	}

}
