
package xbean;

public interface TaskData extends xdb.Bean {
	public TaskData copy(); // deep clone
	public TaskData toData(); // a Data instance
	public TaskData toBean(); // a Bean instance
	public TaskData toDataIf(); // a Data instance If need. else return this
	public TaskData toBeanIf(); // a Bean instance If need. else return this

	public int getTaskid(); // 
	public java.util.Map<Integer, Integer> getCounter(); // 
	public java.util.Map<Integer, Integer> getCounterAsData(); // 

	public void setTaskid(int _v_); // 
}
