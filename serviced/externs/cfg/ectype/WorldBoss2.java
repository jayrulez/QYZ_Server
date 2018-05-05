package cfg.ectype;
public final class WorldBoss2 extends cfg.CfgObject {
	public final static int TYPEID = 1998113247;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final int bossid;
	public final int worldmapid;
	public final cfg.map.Vector2 position;
	public final cfg.cmd.action.OneItems showbonusitems;
	public final String backgroundpic;
	public final String description;
	public final cfg.common.DateTimeRange openrange;
	public final int duration;
	public final cfg.cmd.action.MultiBonus hurtbonus;
	public final String refreshdescription;
	public final java.util.List<cfg.common.DayTime> opentimes = new java.util.ArrayList<>();
	public WorldBoss2(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.bossid = fs.getInt();
		this.worldmapid = fs.getInt();
		this.position = new cfg.map.Vector2(fs);
		this.showbonusitems = new cfg.cmd.action.OneItems(fs);
		this.backgroundpic = fs.getString();
		this.description = fs.getString();
		this.openrange = new cfg.common.DateTimeRange(fs);
		this.duration = fs.getInt();
		this.hurtbonus = new cfg.cmd.action.MultiBonus(fs);
		this.refreshdescription = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.opentimes.add(new cfg.common.DayTime(fs));
		}
	}
}