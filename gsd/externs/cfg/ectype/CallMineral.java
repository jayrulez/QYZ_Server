package cfg.ectype;
public final class CallMineral extends cfg.ectype.Action {
	public final static int TYPEID = 1984752704;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final int time;
	public final cfg.map.Vector2 position;
	public CallMineral(cfg.DataStream fs) {
		super(fs);
		this.id = fs.getInt();
		this.time = fs.getInt();
		this.position = new cfg.map.Vector2(fs);
	}
}