package cfg.item;
public final class ItemLevelUp extends cfg.item.ItemBasic {
	public final static int TYPEID = -1427737477;
	final public int getTypeId() { return TYPEID; }
	public final int leveluptype;
	public final int lvl;
	public ItemLevelUp(cfg.DataStream fs) {
		super(fs);
		this.leveluptype = fs.getInt();
		this.lvl = fs.getInt();
	}
}