
package xbean;

public interface HuiWuProfessionTerm extends xdb.Bean {
	public HuiWuProfessionTerm copy(); // deep clone
	public HuiWuProfessionTerm toData(); // a Data instance
	public HuiWuProfessionTerm toBean(); // a Bean instance
	public HuiWuProfessionTerm toDataIf(); // a Data instance If need. else return this
	public HuiWuProfessionTerm toBeanIf(); // a Bean instance If need. else return this

	public int getProfession(); // 
	public java.util.Set<Long> getEnrollroleids(); // 报名玩家
	public java.util.Set<Long> getEnrollroleidsAsData(); // 报名玩家
	public java.util.List<Long> getPreselection1roleids(); // 筛选玩家
	public java.util.List<Long> getPreselection1roleidsAsData(); // 筛选玩家
	public java.util.List<Long> getPreselection2roleids(); // 预选玩家
	public java.util.List<Long> getPreselection2roleidsAsData(); // 预选玩家
	public java.util.Map<Long, Integer> getPreselection2roleidbeguessnums(); // 预选玩家
	public java.util.Map<Long, Integer> getPreselection2roleidbeguessnumsAsData(); // 预选玩家
	public java.util.Map<Integer, xbean.HuiWuRound> getRounds(); // round -> HuiWuRound
	public java.util.Map<Integer, xbean.HuiWuRound> getRoundsAsData(); // round -> HuiWuRound
	public long getChampion(); // 

	public void setProfession(int _v_); // 
	public void setChampion(long _v_); // 
}
