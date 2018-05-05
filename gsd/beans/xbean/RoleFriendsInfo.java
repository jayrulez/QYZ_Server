
package xbean;

public interface RoleFriendsInfo extends xdb.Bean {
	public RoleFriendsInfo copy(); // deep clone
	public RoleFriendsInfo toData(); // a Data instance
	public RoleFriendsInfo toBean(); // a Bean instance
	public RoleFriendsInfo toDataIf(); // a Data instance If need. else return this
	public RoleFriendsInfo toBeanIf(); // a Bean instance If need. else return this

	public java.util.Map<Long, xbean.RoleFriend> getFriends(); // 好友列表
	public java.util.Map<Long, xbean.RoleFriend> getFriendsAsData(); // 好友列表
	public java.util.Map<Long, Long> getRequesting(); // 申请列表
	public java.util.Map<Long, Long> getRequestingAsData(); // 申请列表
	public java.util.Map<Long, Long> getBlacklist(); // 被屏蔽的
	public java.util.Map<Long, Long> getBlacklistAsData(); // 被屏蔽的
	public int getCharmdegree(); // 个人的魅力值，根据别人送给我的花决定
	public java.util.Map<Long, Integer> getIdolfrienddegree(); // 跟偶像的友好度，key为偶像的id
	public java.util.Map<Long, Integer> getIdolfrienddegreeAsData(); // 跟偶像的友好度，key为偶像的id
	public java.util.Map<Long, xbean.IdolAwardClaim> getIdolawardclaiminfo(); // 偶像奖励的领取情况，key为偶像id，value为领取情况
	public java.util.Map<Long, xbean.IdolAwardClaim> getIdolawardclaiminfoAsData(); // 偶像奖励的领取情况，key为偶像id，value为领取情况
	public java.util.Map<Long, xbean.Enemyinfo> getEnemylist(); // 仇人信息
	public java.util.Map<Long, xbean.Enemyinfo> getEnemylistAsData(); // 仇人信息
	public int getIsallowfriendgetmm(); // 是否允许好友查看脉脉
	public int getIsallowstrangergetmm(); // 是否允许陌生人查看脉脉
	public java.util.Map<Integer, xbean.RoleRelation> getRelationinfo(); // maimai列表,key为脉脉类型
	public java.util.Map<Integer, xbean.RoleRelation> getRelationinfoAsData(); // maimai列表,key为脉脉类型

	public void setCharmdegree(int _v_); // 个人的魅力值，根据别人送给我的花决定
	public void setIsallowfriendgetmm(int _v_); // 是否允许好友查看脉脉
	public void setIsallowstrangergetmm(int _v_); // 是否允许陌生人查看脉脉
}
