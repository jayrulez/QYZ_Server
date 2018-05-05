package cfg.map;
public final class NPCDeployment extends cfg.map.Deployment {
	public final static int TYPEID = -410081206;
	final public int getTypeId() { return TYPEID; }
	public final int npcid;
	public final boolean defaulton;
	public final cfg.map.Vector3 position;
	public final cfg.map.Vector3 orientation;
	public NPCDeployment(cfg.DataStream fs) {
		super(fs);
		this.npcid = fs.getInt();
		this.defaulton = fs.getBool();
		this.position = new cfg.map.Vector3(fs);
		this.orientation = new cfg.map.Vector3(fs);
	}
}