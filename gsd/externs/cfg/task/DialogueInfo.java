package cfg.task;
public final class DialogueInfo extends cfg.CfgObject {
	public final static int TYPEID = 1002471813;
	final public int getTypeId() { return TYPEID; }
	public final int role;
	public final int npcid;
	public final int pos;
	public final String dialogcontent;
	public final int action;
	public final int voiceid;
	public final int delay;
	public final int dialogframetype;
	public DialogueInfo(cfg.DataStream fs) {
		this.role = fs.getInt();
		this.npcid = fs.getInt();
		this.pos = fs.getInt();
		this.dialogcontent = fs.getString();
		this.action = fs.getInt();
		this.voiceid = fs.getInt();
		this.delay = fs.getInt();
		this.dialogframetype = fs.getInt();
	}
}