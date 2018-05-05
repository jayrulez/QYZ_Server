package cfg.equip;
public final class AccessoryProperty extends cfg.CfgObject {
	public final static int TYPEID = 1991720223;
	final public int getTypeId() { return TYPEID; }
	public final int propertyid;
	public final int weight;
	public final float minvalue;
	public final float maxvalue;
	public AccessoryProperty(cfg.DataStream fs) {
		this.propertyid = fs.getInt();
		this.weight = fs.getInt();
		this.minvalue = fs.getFloat();
		this.maxvalue = fs.getFloat();
	}
}