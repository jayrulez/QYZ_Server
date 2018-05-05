
package xbean;

public interface RoleRide extends xdb.Bean {
	public RoleRide copy(); // deep clone
	public RoleRide toData(); // a Data instance
	public RoleRide toBean(); // a Bean instance
	public RoleRide toDataIf(); // a Data instance If need. else return this
	public RoleRide toBeanIf(); // a Bean instance If need. else return this

	public java.util.Map<Integer, xbean.Ride> getRides(); // 角色的坐骑信息
	public java.util.Map<Integer, xbean.Ride> getRidesAsData(); // 角色的坐骑信息
	public int getActiveride(); // 当前激活的坐骑

	public void setActiveride(int _v_); // 当前激活的坐骑
}
