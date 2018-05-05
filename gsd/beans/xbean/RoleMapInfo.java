
package xbean;

public interface RoleMapInfo extends xdb.Bean {
	public RoleMapInfo copy(); // deep clone
	public RoleMapInfo toData(); // a Data instance
	public RoleMapInfo toBean(); // a Bean instance
	public RoleMapInfo toDataIf(); // a Data instance If need. else return this
	public RoleMapInfo toBeanIf(); // a Bean instance If need. else return this

	public long getMapid(); // 
	public xbean.Vector3 getPosition(); // 
	public xbean.Vector3 getOrient(); // 
	public int getRidestatus(); // 

	public void setMapid(long _v_); // 
	public void setRidestatus(int _v_); // 
}
