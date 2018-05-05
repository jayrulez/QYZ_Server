package cfg.map;
public final class MineralDeployment extends cfg.map.Deployment {
	public final static int TYPEID = 1472304915;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<cfg.map.MineralSpawn> minerals = new java.util.ArrayList<>();
	public final cfg.map.DeploymentLocation location;
	public MineralDeployment(cfg.DataStream fs) {
		super(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.minerals.add(new cfg.map.MineralSpawn(fs));
		}
		this.location = (cfg.map.DeploymentLocation)fs.getObject(fs.getString());
	}
}