
package xbean;

public interface AccessoryProp extends xdb.Bean {
	public AccessoryProp copy(); // deep clone
	public AccessoryProp toData(); // a Data instance
	public AccessoryProp toBean(); // a Bean instance
	public AccessoryProp toDataIf(); // a Data instance If need. else return this
	public AccessoryProp toBeanIf(); // a Bean instance If need. else return this

	public int getKey(); // 
	public float getVal(); // 

	public void setKey(int _v_); // 
	public void setVal(float _v_); // 
}
