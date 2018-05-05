package cfg.ectype;
public final class ItemRune extends cfg.ectype.Rune {
	public final static int TYPEID = -845477927;
	final public int getTypeId() { return TYPEID; }
	public final int itemid;
	public final int num;
	public ItemRune(cfg.DataStream fs) {
		super(fs);
		this.itemid = fs.getInt();
		this.num = fs.getInt();
	}
}