package cfg.item;
public final class ItemScene extends cfg.item.ItemBasic {
	public final static int TYPEID = 290936520;
	final public int getTypeId() { return TYPEID; }
	public final int mineralid;
	public final String actionname;
	public final int posoffset;
	public ItemScene(cfg.DataStream fs) {
		super(fs);
		this.mineralid = fs.getInt();
		this.actionname = fs.getString();
		this.posoffset = fs.getInt();
	}
}