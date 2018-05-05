
package xbean;

public interface FamilyTaskDetail extends xdb.Bean {
	public FamilyTaskDetail copy(); // deep clone
	public FamilyTaskDetail toData(); // a Data instance
	public FamilyTaskDetail toBean(); // a Bean instance
	public FamilyTaskDetail toDataIf(); // a Data instance If need. else return this
	public FamilyTaskDetail toBeanIf(); // a Bean instance If need. else return this

	public int getTaskid(); // 环任务中某环的任务id
	public int getNpcid(); // 环任务中某环的npcid，同时也是下一环的任务发放npc

	public void setTaskid(int _v_); // 环任务中某环的任务id
	public void setNpcid(int _v_); // 环任务中某环的npcid，同时也是下一环的任务发放npc
}
