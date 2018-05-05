package cfg.task;
public final class Task extends cfg.CfgObject {
	public final static int TYPEID = 582025060;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String filepath;
	public final cfg.task.TaskBase basic;
	public final cfg.task.TaskAccept accept;
	public final cfg.task.TaskFailed failed;
	public final cfg.task.TaskComplete complete;
	public final cfg.task.TaskReward reward;
	public Task(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.filepath = fs.getString();
		this.basic = new cfg.task.TaskBase(fs);
		this.accept = new cfg.task.TaskAccept(fs);
		this.failed = new cfg.task.TaskFailed(fs);
		this.complete = new cfg.task.TaskComplete(fs);
		this.reward = new cfg.task.TaskReward(fs);
	}
}