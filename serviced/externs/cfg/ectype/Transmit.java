package cfg.ectype;
public final class Transmit extends cfg.ectype.Action {
	public final static int TYPEID = -742058596;
	final public int getTypeId() { return TYPEID; }
	public final cfg.map.Vector2 position;
	public final float rotation;
	public Transmit(cfg.DataStream fs) {
		super(fs);
		this.position = new cfg.map.Vector2(fs);
		this.rotation = fs.getFloat();
	}
}