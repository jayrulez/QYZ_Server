package cfg.ectype;
public final class GuardTower extends cfg.CfgObject {
	public final static int TYPEID = 76246864;
	final public int getTypeId() { return TYPEID; }
	public final cfg.cmd.condition.MinLevel lvlimit;
	public final cfg.cmd.condition.MinLevel hardlvlimit;
	public final int maxteamnum;
	public final cfg.ectype.BroadcastInfo broadcastinfo;
	public final String ease;
	public final String hard;
	public final String easetexture;
	public final String hardtexture;
	public final cfg.ectype.TimeControler opentime;
	public final int last;
	public final cfg.cmd.condition.VipLimitsLite dailylimit;
	public final int preparetime;
	public final String bgmpic;
	public final int matchtimeout;
	public final int entercountdown;
	public final cfg.ectype.Spell spellmsg;
	public final java.util.List<cfg.map.Vector2> bornpositions = new java.util.ArrayList<>();
	public final java.util.List<cfg.ectype.GuardTowerZone> zones = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.ectype.GuardTowerZone> zones_zoneid= new java.util.HashMap<>();
	public GuardTower(cfg.DataStream fs) {
		this.lvlimit = new cfg.cmd.condition.MinLevel(fs);
		this.hardlvlimit = new cfg.cmd.condition.MinLevel(fs);
		this.maxteamnum = fs.getInt();
		this.broadcastinfo = new cfg.ectype.BroadcastInfo(fs);
		this.ease = fs.getString();
		this.hard = fs.getString();
		this.easetexture = fs.getString();
		this.hardtexture = fs.getString();
		this.opentime = new cfg.ectype.TimeControler(fs);
		this.last = fs.getInt();
		this.dailylimit = new cfg.cmd.condition.VipLimitsLite(fs);
		this.preparetime = fs.getInt();
		this.bgmpic = fs.getString();
		this.matchtimeout = fs.getInt();
		this.entercountdown = fs.getInt();
		this.spellmsg = new cfg.ectype.Spell(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.bornpositions.add(new cfg.map.Vector2(fs));
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.ectype.GuardTowerZone _v = new cfg.ectype.GuardTowerZone(fs);
			this.zones.add(_v);
			this.zones_zoneid.put(_v.zoneid, _v);
		}
	}
}