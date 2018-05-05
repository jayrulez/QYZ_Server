
package xbean;

public interface Jewelry extends xdb.Bean {
	public Jewelry copy(); // deep clone
	public Jewelry toData(); // a Data instance
	public Jewelry toBean(); // a Bean instance
	public Jewelry toDataIf(); // a Data instance If need. else return this
	public Jewelry toBeanIf(); // a Bean instance If need. else return this

	public int getId(); // 宝珠id
	public int getLevel(); // 宝珠等级
	public int getExp(); // 宝珠经验

	public void setId(int _v_); // 宝珠id
	public void setLevel(int _v_); // 宝珠等级
	public void setExp(int _v_); // 宝珠经验
}
