package cfg.equip;
public abstract class Equip extends cfg.CfgObject {
	public static final int MAX_APPEND_LEVEL = 90;
	public static final int MAX_ANNEAL_LEVEL = 20;
	public static final int UPGRADE_COST_ITEM = 10400007;
	public static final int DISASSEMBLY_CURRENCY = 10200004;
	public static final int ABANDON_CURRENCY = 10200002;
	public static final int ABANDON_COST = 10;
	public static final int MIN_UPGRADE_LEVEL = 60;
	public static final int UPGRADE_DELTA_LEVEL = 15;
	public static final int MAX_UPGRADE_LEVEL = 120;
	public final int id;
	public final String name;
	public final String icon;
	public final int level;
	public final int type;
	public final int quality;
	public final int prize;
	public final cfg.cmd.action.BindType bindtype;
	public final String introduction;
	public final cfg.cmd.action.LingJing break2lingjing;
	public Equip(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.name = fs.getString();
		this.icon = fs.getString();
		this.level = fs.getInt();
		this.type = fs.getInt();
		this.quality = fs.getInt();
		this.prize = fs.getInt();
		this.bindtype = new cfg.cmd.action.BindType(fs);
		this.introduction = fs.getString();
		this.break2lingjing = new cfg.cmd.action.LingJing(fs);
	}
}