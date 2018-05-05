package cfg.navmesh;
public final class NavMesh extends cfg.CfgObject {
	public final static int TYPEID = 1807125928;
	final public int getTypeId() { return TYPEID; }
	public final String name;
	public final cfg.navmesh.Mesh mesh;
	public NavMesh(cfg.DataStream fs) {
		this.name = fs.getString();
		this.mesh = new cfg.navmesh.Mesh(fs);
	}
}