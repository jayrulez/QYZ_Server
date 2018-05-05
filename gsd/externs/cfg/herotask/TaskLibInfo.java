package cfg.herotask;
public final class TaskLibInfo extends cfg.CfgObject {
	public final static int TYPEID = -778938423;
	final public int getTypeId() { return TYPEID; }
	public final int ident;
	public final int npcid;
	public final int challengetype;
	public final int ectypeid;
	public final String introduction;
	public final String npcdialogue;
	public TaskLibInfo(cfg.DataStream fs) {
		this.ident = fs.getInt();
		this.npcid = fs.getInt();
		this.challengetype = fs.getInt();
		this.ectypeid = fs.getInt();
		this.introduction = fs.getString();
		this.npcdialogue = fs.getString();
	}
}