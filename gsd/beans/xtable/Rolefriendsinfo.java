package xtable;

// typed table access point
public class Rolefriendsinfo {
	Rolefriendsinfo() {
	}

	public static xbean.RoleFriendsInfo get(Long key) {
		return _Tables_.getInstance().rolefriendsinfo.get(key);
	}

	public static xbean.RoleFriendsInfo get(Long key, xbean.RoleFriendsInfo value) {
		return _Tables_.getInstance().rolefriendsinfo.get(key, value);
	}

	public static void insert(Long key, xbean.RoleFriendsInfo value) {
		_Tables_.getInstance().rolefriendsinfo.insert(key, value);
	}

	public static void delete(Long key) {
		_Tables_.getInstance().rolefriendsinfo.delete(key);
	}

	public static boolean add(Long key, xbean.RoleFriendsInfo value) {
		return _Tables_.getInstance().rolefriendsinfo.add(key, value);
	}

	public static boolean remove(Long key) {
		return _Tables_.getInstance().rolefriendsinfo.remove(key);
	}

	public static xdb.TTableCache<Long, xbean.RoleFriendsInfo> getCache() {
		return _Tables_.getInstance().rolefriendsinfo.getCache();
	}

	public static xdb.TTable<Long, xbean.RoleFriendsInfo> getTable() {
		return _Tables_.getInstance().rolefriendsinfo;
	}

	public static xbean.RoleFriendsInfo select(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleFriendsInfo, xbean.RoleFriendsInfo>() {
			public xbean.RoleFriendsInfo get(xbean.RoleFriendsInfo v) { return v.toData(); }
		});
	}

	public static java.util.Map<Long, xbean.RoleFriend> selectFriends(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleFriendsInfo, java.util.Map<Long, xbean.RoleFriend>>() {
				public java.util.Map<Long, xbean.RoleFriend> get(xbean.RoleFriendsInfo v) { return v.getFriendsAsData(); }
			});
	}

	public static java.util.Map<Long, Long> selectRequesting(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleFriendsInfo, java.util.Map<Long, Long>>() {
				public java.util.Map<Long, Long> get(xbean.RoleFriendsInfo v) { return v.getRequestingAsData(); }
			});
	}

	public static java.util.Map<Long, Long> selectBlacklist(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleFriendsInfo, java.util.Map<Long, Long>>() {
				public java.util.Map<Long, Long> get(xbean.RoleFriendsInfo v) { return v.getBlacklistAsData(); }
			});
	}

	public static Integer selectCharmdegree(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleFriendsInfo, Integer>() {
				public Integer get(xbean.RoleFriendsInfo v) { return v.getCharmdegree(); }
			});
	}

	public static java.util.Map<Long, Integer> selectIdolfrienddegree(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleFriendsInfo, java.util.Map<Long, Integer>>() {
				public java.util.Map<Long, Integer> get(xbean.RoleFriendsInfo v) { return v.getIdolfrienddegreeAsData(); }
			});
	}

	public static java.util.Map<Long, xbean.IdolAwardClaim> selectIdolawardclaiminfo(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleFriendsInfo, java.util.Map<Long, xbean.IdolAwardClaim>>() {
				public java.util.Map<Long, xbean.IdolAwardClaim> get(xbean.RoleFriendsInfo v) { return v.getIdolawardclaiminfoAsData(); }
			});
	}

	public static java.util.Map<Long, xbean.Enemyinfo> selectEnemylist(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleFriendsInfo, java.util.Map<Long, xbean.Enemyinfo>>() {
				public java.util.Map<Long, xbean.Enemyinfo> get(xbean.RoleFriendsInfo v) { return v.getEnemylistAsData(); }
			});
	}

	public static Integer selectIsallowfriendgetmm(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleFriendsInfo, Integer>() {
				public Integer get(xbean.RoleFriendsInfo v) { return v.getIsallowfriendgetmm(); }
			});
	}

	public static Integer selectIsallowstrangergetmm(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleFriendsInfo, Integer>() {
				public Integer get(xbean.RoleFriendsInfo v) { return v.getIsallowstrangergetmm(); }
			});
	}

	public static java.util.Map<Integer, xbean.RoleRelation> selectRelationinfo(Long key) {
		return getTable().select(key, new xdb.TField<xbean.RoleFriendsInfo, java.util.Map<Integer, xbean.RoleRelation>>() {
				public java.util.Map<Integer, xbean.RoleRelation> get(xbean.RoleFriendsInfo v) { return v.getRelationinfoAsData(); }
			});
	}

}
