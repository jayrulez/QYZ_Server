package cfg.ectype;
public final class CircularArea extends cfg.ectype.Area {
	public final static int TYPEID = 1109053624;
	final public int getTypeId() { return TYPEID; }
	public final cfg.map.Vector2 position;
	public final float radius;
	public CircularArea(cfg.DataStream fs) {
		super(fs);
		this.position = new cfg.map.Vector2(fs);
		this.radius = fs.getFloat();
	}
}