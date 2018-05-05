package cfg.equip;
public final class EquipPropertyData extends cfg.CfgObject {
	public final static int TYPEID = 1229267335;
	final public int getTypeId() { return TYPEID; }
	public final int propertytype;
	public final float value;
	public EquipPropertyData(cfg.DataStream fs) {
		this.propertytype = fs.getInt();
		this.value = fs.getFloat();
	}
}