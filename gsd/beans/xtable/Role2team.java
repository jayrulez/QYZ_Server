package xtable;

// typed table access point
public class Role2team {
	Role2team() {
	}

	public static xbean.RoleTeamInfo get(Long key) {
		return _Tables_.getInstance().role2team.get(key);
	}

	public static xbean.RoleTeamInfo get(Long key, xbean.RoleTeamInfo value) {
		return _Tables_.getInstance().role2team.get(key, value);
	}

	public static void insert(Long key, xbean.RoleTeamInfo value) {
		_Tables_.getInstance().role2team.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().role2team.delete(key);
	}

	public static boolean add(Long key, xbean.RoleTeamInfo value) {
		return _Tables_.getInstance().role2team.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().role2team.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.RoleTeamInfo> getCache() {
		return _Tables_.getInstance().role2team.getCache();
	}

	public static xdb.TTable<Long, xbean.RoleTeamInfo> getTable() {
		return _Tables_.getInstance().role2team;
	}

	public static xbean.RoleTeamInfo select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleTeamInfo, xbean.RoleTeamInfo>() {
			public xbean.RoleTeamInfo get(xbean.RoleTeamInfo v) { return v.toData(); }
		});
	}

	public static Long selectTeamid(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleTeamInfo, Long>() {
				public Long get(xbean.RoleTeamInfo v) { return v.getTeamid(); }
			});
	}

	public static Integer selectAutoacceptrequest(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleTeamInfo, Integer>() {
				public Integer get(xbean.RoleTeamInfo v) { return v.getAutoacceptrequest(); }
			});
	}

	public static Integer selectAutoacceptinvite(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleTeamInfo, Integer>() {
				public Integer get(xbean.RoleTeamInfo v) { return v.getAutoacceptinvite(); }
			});
	}

	public static java.util.Map<Long, xbean.TeamMember> selectRequesttojoin(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleTeamInfo, java.util.Map<Long, xbean.TeamMember>>() {
				public java.util.Map<Long, xbean.TeamMember> get(xbean.RoleTeamInfo v) { return v.getRequesttojoinAsData(); }
			});
	}

}
