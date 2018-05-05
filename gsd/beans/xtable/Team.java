package xtable;

// typed table access point
public class Team {
	Team() {
	}

	public static xdb.util.AutoKey<Long> getAutoKey() {
		return _Tables_.getInstance().team.getAutoKey();
	}

	public static Long nextKey() {
		return getAutoKey().next();
	}

	public static Long insert(xbean.Team value) {
		Long next = nextKey();
		insert(next, value);
		return next;
	}

	public static xbean.Team get(Long key) {
		return _Tables_.getInstance().team.get(key);
	}

	public static xbean.Team get(Long key, xbean.Team value) {
		return _Tables_.getInstance().team.get(key, value);
	}

	public static void insert(Long key, xbean.Team value) {
		_Tables_.getInstance().team.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().team.delete(key);
	}

	public static boolean add(Long key, xbean.Team value) {
		return _Tables_.getInstance().team.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().team.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.Team> getCache() {
		return _Tables_.getInstance().team.getCache();
	}

	public static xdb.TTable<Long, xbean.Team> getTable() {
		return _Tables_.getInstance().team;
	}

	public static xbean.Team select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.Team, xbean.Team>() {
			public xbean.Team get(xbean.Team v) { return v.toData(); }
		});
	}

	public static Long selectTeamid(Long key) {
		return getTable().select(key, new xdb.TField<xbean.Team, Long>() {
				public Long get(xbean.Team v) { return v.getTeamid(); }
			});
	}

	public static Long selectLeaderid(Long key) {
		return getTable().select(key, new xdb.TField<xbean.Team, Long>() {
				public Long get(xbean.Team v) { return v.getLeaderid(); }
			});
	}

	public static Long selectCreatetime(Long key) {
		return getTable().select(key, new xdb.TField<xbean.Team, Long>() {
				public Long get(xbean.Team v) { return v.getCreatetime(); }
			});
	}

	public static java.util.Map<Long, xbean.TeamMember> selectMembers(Long key) {
		return getTable().select(key, new xdb.TField<xbean.Team, java.util.Map<Long, xbean.TeamMember>>() {
				public java.util.Map<Long, xbean.TeamMember> get(xbean.Team v) { return v.getMembersAsData(); }
			});
	}

	public static java.util.Map<Long, Long> selectRequestforjoin(Long key) {
		return getTable().select(key, new xdb.TField<xbean.Team, java.util.Map<Long, Long>>() {
				public java.util.Map<Long, Long> get(xbean.Team v) { return v.getRequestforjoinAsData(); }
			});
	}

	public static java.util.Map<Long, Long> selectInvitetojoin(Long key) {
		return getTable().select(key, new xdb.TField<xbean.Team, java.util.Map<Long, Long>>() {
				public java.util.Map<Long, Long> get(xbean.Team v) { return v.getInvitetojoinAsData(); }
			});
	}

	public static java.util.Map<Long, Long> selectInvitefollow(Long key) {
		return getTable().select(key, new xdb.TField<xbean.Team, java.util.Map<Long, Long>>() {
				public java.util.Map<Long, Long> get(xbean.Team v) { return v.getInvitefollowAsData(); }
			});
	}

	public static Long selectLeadertransroleid(Long key) {
		return getTable().select(key, new xdb.TField<xbean.Team, Long>() {
				public Long get(xbean.Team v) { return v.getLeadertransroleid(); }
			});
	}

}
