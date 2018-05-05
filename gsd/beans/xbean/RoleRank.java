
package xbean;

public interface RoleRank extends xdb.Bean {
	public RoleRank copy(); // deep clone
	public RoleRank toData(); // a Data instance
	public RoleRank toBean(); // a Bean instance
	public RoleRank toDataIf(); // a Data instance If need. else return this
	public RoleRank toBeanIf(); // a Bean instance If need. else return this

	public java.util.Map<Integer, xbean.ARankInfo> getRanks(); // key -> ranktype
	public java.util.Map<Integer, xbean.ARankInfo> getRanksAsData(); // key -> ranktype

}
