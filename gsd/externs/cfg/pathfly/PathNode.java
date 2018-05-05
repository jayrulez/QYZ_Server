package cfg.pathfly;
public final class PathNode extends cfg.CfgObject {
	public final static int TYPEID = 1578111985;
	final public int getTypeId() { return TYPEID; }
	public final float time;
	public final cfg.pathfly.Vector3 position;
	public final cfg.pathfly.Vector3 rotation;
	public final cfg.pathfly.Vector3 localscale;
	public final cfg.pathfly.Vector3 intangent;
	public final cfg.pathfly.Vector3 outtangent;
	public PathNode(cfg.DataStream fs) {
		this.time = fs.getFloat();
		this.position = new cfg.pathfly.Vector3(fs);
		this.rotation = new cfg.pathfly.Vector3(fs);
		this.localscale = new cfg.pathfly.Vector3(fs);
		this.intangent = new cfg.pathfly.Vector3(fs);
		this.outtangent = new cfg.pathfly.Vector3(fs);
	}
}