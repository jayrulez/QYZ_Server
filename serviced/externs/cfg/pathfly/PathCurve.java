package cfg.pathfly;
public final class PathCurve extends cfg.CfgObject {
	public final static int TYPEID = 1666865376;
	final public int getTypeId() { return TYPEID; }
	public final String mode;
	public final boolean constspeed;
	public final float speed;
	public final boolean positionvary;
	public final boolean rotationvary;
	public final boolean scalevary;
	public final java.util.List<cfg.pathfly.PathNode> nodes = new java.util.ArrayList<>();
	public PathCurve(cfg.DataStream fs) {
		this.mode = fs.getString();
		this.constspeed = fs.getBool();
		this.speed = fs.getFloat();
		this.positionvary = fs.getBool();
		this.rotationvary = fs.getBool();
		this.scalevary = fs.getBool();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.nodes.add(new cfg.pathfly.PathNode(fs));
		}
	}
}