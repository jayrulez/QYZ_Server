package cfg.ectype;
public final class PathFinding extends cfg.ectype.Action {
	public final static int TYPEID = -1774415560;
	final public int getTypeId() { return TYPEID; }
	public final String content;
	public final int curveid;
	public PathFinding(cfg.DataStream fs) {
		super(fs);
		this.content = fs.getString();
		this.curveid = fs.getInt();
	}
}