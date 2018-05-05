
package xbean;

public interface RoleMarriage extends xdb.Bean {
	public RoleMarriage copy(); // deep clone
	public RoleMarriage toData(); // a Data instance
	public RoleMarriage toBean(); // a Bean instance
	public RoleMarriage toDataIf(); // a Data instance If need. else return this
	public RoleMarriage toBeanIf(); // a Bean instance If need. else return this

	public long getCoupleroleid(); // 对象的roleid，暂时存放在这里，以后可能存在脉脉中
	public long getCurproposeid(); // 当前正在求婚对象的id
	public long getStartproposetime(); // 开始求婚的时间,如果一段时间后没有反应，那么可以向别的人求婚
	public java.util.List<Long> getWishfriendlist(); // 求婚后发送祝福的好友
	public java.util.List<Long> getWishfriendlistAsData(); // 求婚后发送祝福的好友

	public void setCoupleroleid(long _v_); // 对象的roleid，暂时存放在这里，以后可能存在脉脉中
	public void setCurproposeid(long _v_); // 当前正在求婚对象的id
	public void setStartproposetime(long _v_); // 开始求婚的时间,如果一段时间后没有反应，那么可以向别的人求婚
}
