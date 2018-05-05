
package xbean;

public interface BoardInfo extends xdb.Bean {
	public BoardInfo copy(); // deep clone
	public BoardInfo toData(); // a Data instance
	public BoardInfo toBean(); // a Bean instance
	public BoardInfo toDataIf(); // a Data instance If need. else return this
	public BoardInfo toBeanIf(); // a Bean instance If need. else return this

	public java.util.Map<Integer, xbean.BoardEntry> getLatestboard(); // 
	public java.util.Map<Integer, xbean.BoardEntry> getLatestboardAsData(); // 
	public java.util.Map<Long, Integer> getRolerank(); // 
	public java.util.Map<Long, Integer> getRolerankAsData(); // 
	public long getLastupdatetime(); // 
	public java.util.Map<Long, Integer> getYesterdayrank(); // 
	public java.util.Map<Long, Integer> getYesterdayrankAsData(); // 

	public void setLastupdatetime(long _v_); // 
}
