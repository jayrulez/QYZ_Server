package cfg.item;
public final class ItemCompose extends cfg.CfgObject {
	public final static int TYPEID = -547024690;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final int targetid;
	public final cfg.cmd.condition.Items requireitem;
	public final cfg.cmd.action.Bonus getbonus;
	public ItemCompose(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.targetid = fs.getInt();
		this.requireitem = new cfg.cmd.condition.Items(fs);
		this.getbonus = (cfg.cmd.action.Bonus)fs.getObject(fs.getString());
	}
}