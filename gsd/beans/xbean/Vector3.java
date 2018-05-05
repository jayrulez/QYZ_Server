
package xbean;

public interface Vector3 extends xdb.Bean {
	public Vector3 copy(); // deep clone
	public Vector3 toData(); // a Data instance
	public Vector3 toBean(); // a Bean instance
	public Vector3 toDataIf(); // a Data instance If need. else return this
	public Vector3 toBeanIf(); // a Bean instance If need. else return this

	public float getX(); // 
	public float getY(); // 
	public float getZ(); // 

	public void setX(float _v_); // 
	public void setY(float _v_); // 
	public void setZ(float _v_); // 
}
