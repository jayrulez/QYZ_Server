package cfg.family;
public final class FamilyTask extends cfg.CfgObject {
	public final static int TYPEID = 1736531177;
	final public int getTypeId() { return TYPEID; }
	public final int task;
	public final int npc;
	public FamilyTask(cfg.DataStream fs) {
		this.task = fs.getInt();
		this.npc = fs.getInt();
	}
}