package cfg.ectype;
public final class RegionEffect extends cfg.CfgObject {
	public final static int TYPEID = 646492817;
	final public int getTypeId() { return TYPEID; }
	public final String effect;
	public final String name;
	public final cfg.map.Vector2 position;
	public final int angle;
	public RegionEffect(cfg.DataStream fs) {
		this.effect = fs.getString();
		this.name = fs.getString();
		this.position = new cfg.map.Vector2(fs);
		this.angle = fs.getInt();
	}
}