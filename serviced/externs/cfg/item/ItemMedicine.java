package cfg.item;
public final class ItemMedicine extends cfg.item.ItemBasic {
	public final static int TYPEID = -79257986;
	final public int getTypeId() { return TYPEID; }
	public final int buffid;
	public final int hp;
	public final int mp;
	public final cfg.cmd.condition.GroupCoolDown cdgroup;
	public final int medicinetype;
	public final String roundicon;
	public ItemMedicine(cfg.DataStream fs) {
		super(fs);
		this.buffid = fs.getInt();
		this.hp = fs.getInt();
		this.mp = fs.getInt();
		this.cdgroup = new cfg.cmd.condition.GroupCoolDown(fs);
		this.medicinetype = fs.getInt();
		this.roundicon = fs.getString();
	}
}