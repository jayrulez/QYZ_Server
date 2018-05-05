
package xbean;

public interface Limit extends xdb.Bean {
	public Limit copy(); // deep clone
	public Limit toData(); // a Data instance
	public Limit toBean(); // a Bean instance
	public Limit toDataIf(); // a Data instance If need. else return this
	public Limit toBeanIf(); // a Bean instance If need. else return this

	public long getId(); // 
	public java.util.Map<Integer, Integer> getTypenums(); // limittype -> num 每种限制类型相应的已达到的次数
	public java.util.Map<Integer, Integer> getTypenumsAsData(); // limittype -> num 每种限制类型相应的已达到的次数
	public int getLastbuytime(); // 

	public void setId(long _v_); // 
	public void setLastbuytime(int _v_); // 
}
