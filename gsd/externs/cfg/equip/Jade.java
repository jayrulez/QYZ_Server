package cfg.equip;
public final class Jade extends cfg.CfgObject {
	public final static int TYPEID = -69483808;
	final public int getTypeId() { return TYPEID; }
	public final int jadelvl;
	public final String icon;
	public final cfg.cmd.condition.MinLevel requirelvl;
	public final cfg.cmd.condition.Item requireitem;
	public final float percent;
	public final int maxbonus;
	public Jade(cfg.DataStream fs) {
		this.jadelvl = fs.getInt();
		this.icon = fs.getString();
		this.requirelvl = new cfg.cmd.condition.MinLevel(fs);
		this.requireitem = new cfg.cmd.condition.Item(fs);
		this.percent = fs.getFloat();
		this.maxbonus = fs.getInt();
	}
}