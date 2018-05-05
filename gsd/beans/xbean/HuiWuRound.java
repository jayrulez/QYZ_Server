
package xbean;

public interface HuiWuRound extends xdb.Bean {
	public HuiWuRound copy(); // deep clone
	public HuiWuRound toData(); // a Data instance
	public HuiWuRound toBean(); // a Bean instance
	public HuiWuRound toDataIf(); // a Data instance If need. else return this
	public HuiWuRound toBeanIf(); // a Bean instance If need. else return this

	public java.util.List<xbean.HuiWuBattle> getBattles(); // 
	public java.util.List<xbean.HuiWuBattle> getBattlesAsData(); // 

}
