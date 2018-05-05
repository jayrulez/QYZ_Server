
package xbean;

public interface RoleAchievement extends xdb.Bean {
	public RoleAchievement copy(); // deep clone
	public RoleAchievement toData(); // a Data instance
	public RoleAchievement toBean(); // a Bean instance
	public RoleAchievement toDataIf(); // a Data instance If need. else return this
	public RoleAchievement toBeanIf(); // a Bean instance If need. else return this

	public java.util.Map<Integer, Integer> getAchievementstates(); // 
	public java.util.Map<Integer, Integer> getAchievementstatesAsData(); // 
	public java.util.Map<Integer, Long> getCounters(); // 
	public java.util.Map<Integer, Long> getCountersAsData(); // 
	public java.util.Map<Integer, xbean.CounterSet> getCountersets(); // 
	public java.util.Map<Integer, xbean.CounterSet> getCountersetsAsData(); // 

}
