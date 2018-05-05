package cfg.map;
public abstract class Deployment extends cfg.CfgObject {
	public final int deploymentid;
	public Deployment(cfg.DataStream fs) {
		this.deploymentid = fs.getInt();
	}
}