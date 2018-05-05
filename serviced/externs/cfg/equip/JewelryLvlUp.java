package cfg.equip;
public final class JewelryLvlUp extends cfg.CfgObject {
	public final static int TYPEID = 843515291;
	final public int getTypeId() { return TYPEID; }
	public final int jewelrylvl;
	public final int requireexp;
	public final float basicvalue;
	public JewelryLvlUp(cfg.DataStream fs) {
		this.jewelrylvl = fs.getInt();
		this.requireexp = fs.getInt();
		this.basicvalue = fs.getFloat();
	}
}