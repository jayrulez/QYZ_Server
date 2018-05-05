package cfg.map;
public final class SFXDeployment extends cfg.map.Deployment {
	public final static int TYPEID = 2045770990;
	final public int getTypeId() { return TYPEID; }
	public final int sfxid;
	public final cfg.map.MultiPoints location;
	public SFXDeployment(cfg.DataStream fs) {
		super(fs);
		this.sfxid = fs.getInt();
		this.location = new cfg.map.MultiPoints(fs);
	}
}