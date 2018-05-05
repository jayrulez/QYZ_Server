
package xbean;

public interface TaskHistory extends xdb.Bean {
	public TaskHistory copy(); // deep clone
	public TaskHistory toData(); // a Data instance
	public TaskHistory toBean(); // a Bean instance
	public TaskHistory toDataIf(); // a Data instance If need. else return this
	public TaskHistory toBeanIf(); // a Bean instance If need. else return this

	public int getTaskid(); // 
	public int getCount(); // 
	public long getExpiretime(); // 

	public void setTaskid(int _v_); // 
	public void setCount(int _v_); // 
	public void setExpiretime(long _v_); // 
}
