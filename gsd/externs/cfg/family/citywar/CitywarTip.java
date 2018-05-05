package cfg.family.citywar;
public final class CitywarTip extends cfg.CfgObject {
	public final static int TYPEID = -169774961;
	final public int getTypeId() { return TYPEID; }
	public final int stage;
	public final String content;
	public CitywarTip(cfg.DataStream fs) {
		this.stage = fs.getInt();
		this.content = fs.getString();
	}
}