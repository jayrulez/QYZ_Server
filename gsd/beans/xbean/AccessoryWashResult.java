
package xbean;

public interface AccessoryWashResult extends xdb.Bean {
	public AccessoryWashResult copy(); // deep clone
	public AccessoryWashResult toData(); // a Data instance
	public AccessoryWashResult toBean(); // a Bean instance
	public AccessoryWashResult toDataIf(); // a Data instance If need. else return this
	public AccessoryWashResult toBeanIf(); // a Bean instance If need. else return this

	public int getOldpropindex(); // 旧的key
	public xbean.AccessoryProp getNewprop(); // 新属性key
	public boolean getNeedbind(); // 需要设置成绑定

	public void setOldpropindex(int _v_); // 旧的key
	public void setNeedbind(boolean _v_); // 需要设置成绑定
}
