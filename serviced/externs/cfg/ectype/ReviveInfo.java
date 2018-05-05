package cfg.ectype;
public final class ReviveInfo extends cfg.CfgObject {
	public final static int TYPEID = 140662671;
	final public int getTypeId() { return TYPEID; }
	public final int type;
	public final int maxcount;
	public final cfg.map.Vector2 position;
	public final int time;
	public final cfg.cmd.condition.Currency cost;
	public ReviveInfo(cfg.DataStream fs) {
		this.type = fs.getInt();
		this.maxcount = fs.getInt();
		this.position = new cfg.map.Vector2(fs);
		this.time = fs.getInt();
		this.cost = new cfg.cmd.condition.Currency(fs);
	}
}