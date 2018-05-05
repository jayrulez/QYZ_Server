package cfg.item;
public final class ItemFlower extends cfg.item.ItemBasic {
	public final static int TYPEID = 65536927;
	final public int getTypeId() { return TYPEID; }
	public final int flowertype;
	public final int frienddegree;
	public final int charmdegree;
	public final String image;
	public ItemFlower(cfg.DataStream fs) {
		super(fs);
		this.flowertype = fs.getInt();
		this.frienddegree = fs.getInt();
		this.charmdegree = fs.getInt();
		this.image = fs.getString();
	}
}