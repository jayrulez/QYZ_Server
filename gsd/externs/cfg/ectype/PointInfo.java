package cfg.ectype;
public final class PointInfo extends cfg.CfgObject {
	public final static int TYPEID = -1017642766;
	final public int getTypeId() { return TYPEID; }
	public final cfg.map.Vector2 position;
	public final float orient;
	public PointInfo(cfg.DataStream fs) {
		this.position = new cfg.map.Vector2(fs);
		this.orient = fs.getFloat();
	}
}