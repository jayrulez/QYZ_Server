package cfg.map;
public final class MonsterDeployment extends cfg.map.Deployment {
	public final static int TYPEID = 1953932291;
	final public int getTypeId() { return TYPEID; }
	public final boolean defaulton;
	public final int patroltype;
	public final int regeneratetype;
	public final java.util.List<cfg.map.MonsterSpawn> monsters = new java.util.ArrayList<>();
	public final cfg.map.DeploymentLocation location;
	public final cfg.map.BezierCurve path;
	public MonsterDeployment(cfg.DataStream fs) {
		super(fs);
		this.defaulton = fs.getBool();
		this.patroltype = fs.getInt();
		this.regeneratetype = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.monsters.add(new cfg.map.MonsterSpawn(fs));
		}
		this.location = (cfg.map.DeploymentLocation)fs.getObject(fs.getString());
		this.path = new cfg.map.BezierCurve(fs);
	}
}