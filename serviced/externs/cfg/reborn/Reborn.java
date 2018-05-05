package cfg.reborn;
public final class Reborn extends cfg.CfgObject {
	public final static int TYPEID = -1240917436;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String name;
	public final String desc;
	public final String backgroundpic;
	public final cfg.common.DateTimeRange opentime;
	public final cfg.common.DateTimeRange awardtime;
	public final int needitem;
	public final String itemdecs;
	public final cfg.cmd.condition.DayLimit dailyup;
	public final cfg.reborn.NpcMsg npcmsg1;
	public final cfg.reborn.NpcMsg npcmsg2;
	public final String aimicon;
	public final cfg.reborn.Transparency transparency;
	public final cfg.cmd.action.MultiBonus signupbonus;
	public final int gradeneedtimes;
	public final cfg.cmd.action.MultiBonus dailybonus;
	public final cfg.cmd.action.MultiBonus showdailybonus;
	public Reborn(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.name = fs.getString();
		this.desc = fs.getString();
		this.backgroundpic = fs.getString();
		this.opentime = new cfg.common.DateTimeRange(fs);
		this.awardtime = new cfg.common.DateTimeRange(fs);
		this.needitem = fs.getInt();
		this.itemdecs = fs.getString();
		this.dailyup = new cfg.cmd.condition.DayLimit(fs);
		this.npcmsg1 = new cfg.reborn.NpcMsg(fs);
		this.npcmsg2 = new cfg.reborn.NpcMsg(fs);
		this.aimicon = fs.getString();
		this.transparency = new cfg.reborn.Transparency(fs);
		this.signupbonus = new cfg.cmd.action.MultiBonus(fs);
		this.gradeneedtimes = fs.getInt();
		this.dailybonus = new cfg.cmd.action.MultiBonus(fs);
		this.showdailybonus = new cfg.cmd.action.MultiBonus(fs);
	}
}