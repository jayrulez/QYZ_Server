
package xbean;

public interface IdolCharmInfo extends xdb.Bean {
	public IdolCharmInfo copy(); // deep clone
	public IdolCharmInfo toData(); // a Data instance
	public IdolCharmInfo toBean(); // a Bean instance
	public IdolCharmInfo toDataIf(); // a Data instance If need. else return this
	public IdolCharmInfo toBeanIf(); // a Bean instance If need. else return this

	public int getCharm(); // 
	public long getGuardid(); // 
	public int getGuarddegree(); // 
	public long getGuardtime(); // 

	public void setCharm(int _v_); // 
	public void setGuardid(long _v_); // 
	public void setGuarddegree(int _v_); // 
	public void setGuardtime(long _v_); // 
}
