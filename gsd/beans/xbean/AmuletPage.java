
package xbean;

public interface AmuletPage extends xdb.Bean {
	public AmuletPage copy(); // deep clone
	public AmuletPage toData(); // a Data instance
	public AmuletPage toBean(); // a Bean instance
	public AmuletPage toDataIf(); // a Data instance If need. else return this
	public AmuletPage toBeanIf(); // a Bean instance If need. else return this

	public int getPageindex(); // 护符页的id
	public java.util.Map<Integer, xbean.AmuletProperty> getPropmap(); // key是属性index，value是护符属性
	public java.util.Map<Integer, xbean.AmuletProperty> getPropmapAsData(); // key是属性index，value是护符属性
	public java.util.Map<Integer, xbean.AmuletProperty> getLastwashresult(); // 上次洗练结果
	public java.util.Map<Integer, xbean.AmuletProperty> getLastwashresultAsData(); // 上次洗练结果

	public void setPageindex(int _v_); // 护符页的id
}
