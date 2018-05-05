package cfg.ectype;
public final class SpeedRegionInfo extends cfg.CfgObject {
	public final static int TYPEID = -753291011;
	final public int getTypeId() { return TYPEID; }
	public final int regionid;
	public final int needgrade;
	public final boolean isbossregion;
	public final String decs;
	public final cfg.map.Vector2 rebornposition;
	public final cfg.map.Vector2 aimposition;
	public SpeedRegionInfo(cfg.DataStream fs) {
		this.regionid = fs.getInt();
		this.needgrade = fs.getInt();
		this.isbossregion = fs.getBool();
		this.decs = fs.getString();
		this.rebornposition = new cfg.map.Vector2(fs);
		this.aimposition = new cfg.map.Vector2(fs);
	}
}