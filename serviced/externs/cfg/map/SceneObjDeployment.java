package cfg.map;
public final class SceneObjDeployment extends cfg.map.Deployment {
	public final static int TYPEID = 906330284;
	final public int getTypeId() { return TYPEID; }
	public final int objid;
	public final cfg.map.MultiPoints location;
	public SceneObjDeployment(cfg.DataStream fs) {
		super(fs);
		this.objid = fs.getInt();
		this.location = new cfg.map.MultiPoints(fs);
	}
}