package cfg.equip;
public final class GemStoneConfig extends cfg.CfgObject {
	public final static int TYPEID = -1804000736;
	final public int getTypeId() { return TYPEID; }
	public final int slotid;
	public final int slottype;
	public final cfg.cmd.condition.MinLevel openlevel;
	public final int gemstonetype1;
	public final int gemstonetype2;
	public GemStoneConfig(cfg.DataStream fs) {
		this.slotid = fs.getInt();
		this.slottype = fs.getInt();
		this.openlevel = new cfg.cmd.condition.MinLevel(fs);
		this.gemstonetype1 = fs.getInt();
		this.gemstonetype2 = fs.getInt();
	}
}