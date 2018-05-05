package cfg.ectype;
public final class CallSpecialEffect extends cfg.ectype.Action {
	public final static int TYPEID = -219218816;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final int time;
	public final cfg.map.Vector3 position;
	public CallSpecialEffect(cfg.DataStream fs) {
		super(fs);
		this.id = fs.getInt();
		this.time = fs.getInt();
		this.position = new cfg.map.Vector3(fs);
	}
}