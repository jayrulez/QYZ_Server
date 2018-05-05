package cfg.navmesh;
public final class Convex extends cfg.CfgObject {
	public final static int TYPEID = -1767916561;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<Integer> vertexids = new java.util.ArrayList<>();
	public Convex(cfg.DataStream fs) {
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.vertexids.add(fs.getInt());
		}
	}
}