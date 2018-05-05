package cfg.pathfly;
public final class Paths extends cfg.CfgObject {
	public final static int TYPEID = 36960516;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final cfg.pathfly.PathCurve path;
	public Paths(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.path = new cfg.pathfly.PathCurve(fs);
	}
}