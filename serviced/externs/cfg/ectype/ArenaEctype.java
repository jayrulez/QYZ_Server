package cfg.ectype;
public final class ArenaEctype extends cfg.CfgObject {
	public final static int TYPEID = 725708883;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final int mainregionid;
	public final int airwallregionid;
	public final cfg.map.Vector2 playerbornposition;
	public final cfg.map.Vector2 aibornposition;
	public final int countdown;
	public ArenaEctype(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.mainregionid = fs.getInt();
		this.airwallregionid = fs.getInt();
		this.playerbornposition = new cfg.map.Vector2(fs);
		this.aibornposition = new cfg.map.Vector2(fs);
		this.countdown = fs.getInt();
	}
}