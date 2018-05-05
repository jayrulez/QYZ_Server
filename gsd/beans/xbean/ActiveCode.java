
package xbean;

public interface ActiveCode extends xdb.Bean {
	public ActiveCode copy(); // deep clone
	public ActiveCode toData(); // a Data instance
	public ActiveCode toBean(); // a Bean instance
	public ActiveCode toDataIf(); // a Data instance If need. else return this
	public ActiveCode toBeanIf(); // a Bean instance If need. else return this

	public long getLastusetime(); // 
	public int getTodayusecount(); // 
	public int getTotalusecount(); // 

	public void setLastusetime(long _v_); // 
	public void setTodayusecount(int _v_); // 
	public void setTotalusecount(int _v_); // 
}
