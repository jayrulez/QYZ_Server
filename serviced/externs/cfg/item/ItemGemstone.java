package cfg.item;
public final class ItemGemstone extends cfg.item.ItemBasic {
	public final static int TYPEID = -1678775622;
	final public int getTypeId() { return TYPEID; }
	public final int previd;
	public final int composecost;
	public final int type1;
	public final int type2;
	public final cfg.equip.EquipPropertyData property;
	public ItemGemstone(cfg.DataStream fs) {
		super(fs);
		this.previd = fs.getInt();
		this.composecost = fs.getInt();
		this.type1 = fs.getInt();
		this.type2 = fs.getInt();
		this.property = new cfg.equip.EquipPropertyData(fs);
	}
}