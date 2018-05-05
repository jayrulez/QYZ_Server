package cfg.map;
public final class Controller extends cfg.CfgObject {
	public final static int TYPEID = 1441483352;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final java.util.List<cfg.map.Deployment> deployments = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.map.Deployment> deployments_deploymentid= new java.util.HashMap<>();
	public Controller(cfg.DataStream fs) {
		this.id = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.map.Deployment _v = (cfg.map.Deployment)fs.getObject(fs.getString());
			this.deployments.add(_v);
			this.deployments_deploymentid.put(_v.deploymentid, _v);
		}
	}
}