
package xbean;

public interface DailyArena extends xdb.Bean {
	public DailyArena copy(); // deep clone
	public DailyArena toData(); // a Data instance
	public DailyArena toBean(); // a Bean instance
	public DailyArena toDataIf(); // a Data instance If need. else return this
	public DailyArena toBeanIf(); // a Bean instance If need. else return this

	public int getChallengesuccnum(); // 
	public java.util.List<Integer> getObtainrewards(); // 
	public java.util.List<Integer> getObtainrewardsAsData(); // 

	public void setChallengesuccnum(int _v_); // 
}
