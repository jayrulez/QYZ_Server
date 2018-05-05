
package xbean;

public interface RoleTitle extends xdb.Bean {
	public RoleTitle copy(); // deep clone
	public RoleTitle toData(); // a Data instance
	public RoleTitle toBean(); // a Bean instance
	public RoleTitle toDataIf(); // a Data instance If need. else return this
	public RoleTitle toBeanIf(); // a Bean instance If need. else return this

	public int getActivekey(); // 当前激活的称号id
	public int getActivetype(); // 当前激活的称号的类型
	public java.util.Map<Integer, xbean.GroupTitle> getTitleinfo(); // 称号信息，key为称号的类型，如战力榜，竞技场榜，财富榜，成就榜等
	public java.util.Map<Integer, xbean.GroupTitle> getTitleinfoAsData(); // 称号信息，key为称号的类型，如战力榜，竞技场榜，财富榜，成就榜等

	public void setActivekey(int _v_); // 当前激活的称号id
	public void setActivetype(int _v_); // 当前激活的称号的类型
}
