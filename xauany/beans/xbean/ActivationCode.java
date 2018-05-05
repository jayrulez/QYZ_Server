
package xbean;

public interface ActivationCode extends xdb.Bean {
	public ActivationCode copy(); // deep clone
	public ActivationCode toData(); // a Data instance
	public ActivationCode toBean(); // a Bean instance
	public ActivationCode toDataIf(); // a Data instance If need. else return this
	public ActivationCode toBeanIf(); // a Bean instance If need. else return this

	public final static int STATE_NEW = 0; // 
	public final static int STATE_ALLOCATE = 1; // 
	public final static int STATE_CONFIRM = 2; // 
	public final static int ALLOCATE_TIMEOUT_SECONDS = 30; // 

	public int getType(); // 
	public int getStatus(); // 
	public long getUsetime(); // 

	public void setType(int _v_); // 
	public void setStatus(int _v_); // 
	public void setUsetime(long _v_); // 
}
