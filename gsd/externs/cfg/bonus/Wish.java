package cfg.bonus;
public final class Wish extends cfg.CfgObject {
	public final static int TYPEID = -1623346944;
	final public int getTypeId() { return TYPEID; }
	public final int wishitem;
	public final cfg.cmd.action.Item bonus;
	public Wish(cfg.DataStream fs) {
		this.wishitem = fs.getInt();
		this.bonus = new cfg.cmd.action.Item(fs);
	}
}