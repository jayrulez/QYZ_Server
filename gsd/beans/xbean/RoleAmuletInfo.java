
package xbean;

public interface RoleAmuletInfo extends xdb.Bean {
	public RoleAmuletInfo copy(); // deep clone
	public RoleAmuletInfo toData(); // a Data instance
	public RoleAmuletInfo toBean(); // a Bean instance
	public RoleAmuletInfo toDataIf(); // a Data instance If need. else return this
	public RoleAmuletInfo toBeanIf(); // a Bean instance If need. else return this

	public java.util.Map<Integer, xbean.AmuletPage> getPagemap(); // 护符页信息,key为页id，1,2,3
	public java.util.Map<Integer, xbean.AmuletPage> getPagemapAsData(); // 护符页信息,key为页id，1,2,3

}
