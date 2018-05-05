
package xbean;

public interface HuiWuPriviousTerm extends xdb.Bean {
	public HuiWuPriviousTerm copy(); // deep clone
	public HuiWuPriviousTerm toData(); // a Data instance
	public HuiWuPriviousTerm toBean(); // a Bean instance
	public HuiWuPriviousTerm toDataIf(); // a Data instance If need. else return this
	public HuiWuPriviousTerm toBeanIf(); // a Bean instance If need. else return this

	public int getTermid(); // 
	public long getOpentime(); // 
	public long getEndtime(); // 
	public java.util.Map<Integer, xbean.HuiWuChampion> getChampions(); // profession -> HuiWuChampion
	public java.util.Map<Integer, xbean.HuiWuChampion> getChampionsAsData(); // profession -> HuiWuChampion

	public void setTermid(int _v_); // 
	public void setOpentime(long _v_); // 
	public void setEndtime(long _v_); // 
}
