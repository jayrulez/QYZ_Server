package cfg.navmesh;
public final class Mesh extends cfg.CfgObject {
	public final static int TYPEID = -1409370891;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<cfg.navmesh.Vector3> vertexs = new java.util.ArrayList<>();
	public final java.util.List<cfg.navmesh.Convex> convexs = new java.util.ArrayList<>();
	public Mesh(cfg.DataStream fs) {
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.vertexs.add(new cfg.navmesh.Vector3(fs));
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.convexs.add(new cfg.navmesh.Convex(fs));
		}
	}
}