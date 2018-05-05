
package xbean;

public interface RoleMemInfo extends xdb.Bean {
	public RoleMemInfo copy(); // deep clone
	public RoleMemInfo toData(); // a Data instance
	public RoleMemInfo toBean(); // a Bean instance
	public RoleMemInfo toDataIf(); // a Data instance If need. else return this
	public RoleMemInfo toBeanIf(); // a Bean instance If need. else return this

	public java.util.Map<Integer, xbean.HeroesGroupMemInfo> getHerogroupmeminfos(); // 青云英雄录内存数据
	public java.util.Map<Integer, xbean.HeroesGroupMemInfo> getHerogroupmeminfosAsData(); // 青云英雄录内存数据

}
