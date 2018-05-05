
package xbean;

public interface RoleDress extends xdb.Bean {
	public RoleDress copy(); // deep clone
	public RoleDress toData(); // a Data instance
	public RoleDress toBean(); // a Bean instance
	public RoleDress toDataIf(); // a Data instance If need. else return this
	public RoleDress toBeanIf(); // a Bean instance If need. else return this

	public java.util.Map<Integer, xbean.Dress> getDresses(); // 角色的时装信息
	public java.util.Map<Integer, xbean.Dress> getDressesAsData(); // 角色的时装信息
	public int getActivedress(); // 当前激活的时装

	public void setActivedress(int _v_); // 当前激活的时装
}
