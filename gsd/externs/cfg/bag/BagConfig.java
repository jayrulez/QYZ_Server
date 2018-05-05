package cfg.bag;
public final class BagConfig extends cfg.CfgObject {
	public final static int TYPEID = 735872730;
	final public int getTypeId() { return TYPEID; }
	public static final int DEPOT_NPCID = 23000455;
	public final int bagtype;
	public final boolean stackable;
	public final int initcapacity;
	public final int maxcapacity;
	public final cfg.cmd.condition.FixCurrency unlockgridcost;
	public final cfg.cmd.condition.CoolDown sortcd;
	public BagConfig(cfg.DataStream fs) {
		this.bagtype = fs.getInt();
		this.stackable = fs.getBool();
		this.initcapacity = fs.getInt();
		this.maxcapacity = fs.getInt();
		this.unlockgridcost = (cfg.cmd.condition.FixCurrency)fs.getObject(fs.getString());
		this.sortcd = new cfg.cmd.condition.CoolDown(fs);
	}
}