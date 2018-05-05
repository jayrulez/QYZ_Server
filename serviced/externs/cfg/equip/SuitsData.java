package cfg.equip;
public final class SuitsData extends cfg.CfgObject {
	public final static int TYPEID = 1006058984;
	final public int getTypeId() { return TYPEID; }
	public final cfg.equip.EquipPropertyData propertydata;
	public final int amountlimit;
	public SuitsData(cfg.DataStream fs) {
		this.propertydata = new cfg.equip.EquipPropertyData(fs);
		this.amountlimit = fs.getInt();
	}
}