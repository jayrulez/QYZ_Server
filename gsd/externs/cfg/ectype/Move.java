package cfg.ectype;
public final class Move extends cfg.ectype.Action {
	public final static int TYPEID = 1772999869;
	final public int getTypeId() { return TYPEID; }
	public final int targetid;
	public final int pathid;
	public final cfg.map.Vector3 rotation;
	public Move(cfg.DataStream fs) {
		super(fs);
		this.targetid = fs.getInt();
		this.pathid = fs.getInt();
		this.rotation = new cfg.map.Vector3(fs);
	}
}