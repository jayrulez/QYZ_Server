
package xbean;

public interface FamilyLogReport extends xdb.Bean {
	public FamilyLogReport copy(); // deep clone
	public FamilyLogReport toData(); // a Data instance
	public FamilyLogReport toBean(); // a Bean instance
	public FamilyLogReport toDataIf(); // a Data instance If need. else return this
	public FamilyLogReport toBeanIf(); // a Bean instance If need. else return this

	public int getActiontype(); // 家族日志类型，入帮，升职等等,参考协议中的bean定义
	public long getRoleid(); // 角色id
	public long getActiontime(); // 日志记录的时间
	public int getNumber(); // 操作对应的数值

	public void setActiontype(int _v_); // 家族日志类型，入帮，升职等等,参考协议中的bean定义
	public void setRoleid(long _v_); // 角色id
	public void setActiontime(long _v_); // 日志记录的时间
	public void setNumber(int _v_); // 操作对应的数值
}
