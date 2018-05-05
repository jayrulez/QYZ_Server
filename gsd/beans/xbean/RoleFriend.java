
package xbean;

public interface RoleFriend extends xdb.Bean {
	public RoleFriend copy(); // deep clone
	public RoleFriend toData(); // a Data instance
	public RoleFriend toBean(); // a Bean instance
	public RoleFriend toDataIf(); // a Data instance If need. else return this
	public RoleFriend toBeanIf(); // a Bean instance If need. else return this

	public int getFrienddegress(); // 我与好友的友好度 互送花决定
	public int getRelation(); // 我与好友的脉脉关系
	public long getTime(); // 我与好友的脉脉关系

	public void setFrienddegress(int _v_); // 我与好友的友好度 互送花决定
	public void setRelation(int _v_); // 我与好友的脉脉关系
	public void setTime(long _v_); // 我与好友的脉脉关系
}
