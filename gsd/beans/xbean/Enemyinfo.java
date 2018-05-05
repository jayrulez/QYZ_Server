
package xbean;

public interface Enemyinfo extends xdb.Bean {
	public Enemyinfo copy(); // deep clone
	public Enemyinfo toData(); // a Data instance
	public Enemyinfo toBean(); // a Bean instance
	public Enemyinfo toDataIf(); // a Data instance If need. else return this
	public Enemyinfo toBeanIf(); // a Bean instance If need. else return this

	public int getBekillnum(); // 
	public int getKillnum(); // 
	public long getLastbekilltime(); // 最后被击杀的时间

	public void setBekillnum(int _v_); // 
	public void setKillnum(int _v_); // 
	public void setLastbekilltime(long _v_); // 最后被击杀的时间
}
