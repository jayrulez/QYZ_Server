
package xbean;

public interface RandomBonus extends xdb.Bean {
	public RandomBonus copy(); // deep clone
	public RandomBonus toData(); // a Data instance
	public RandomBonus toBean(); // a Bean instance
	public RandomBonus toDataIf(); // a Data instance If need. else return this
	public RandomBonus toBeanIf(); // a Bean instance If need. else return this

	public int getBindtype(); // 
	public java.util.Map<Integer, Integer> getItems(); // 
	public java.util.Map<Integer, Integer> getItemsAsData(); // 

	public void setBindtype(int _v_); // 
}
