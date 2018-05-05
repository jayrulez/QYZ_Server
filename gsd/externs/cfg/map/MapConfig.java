package cfg.map;
public final class MapConfig extends cfg.CfgObject {
	public final static int TYPEID = -493817470;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public MapConfig(cfg.DataStream fs) {
		this.id = fs.getInt();
	}
}