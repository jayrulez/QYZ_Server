package cfg.item;
public abstract class ItemBasic extends cfg.CfgObject {
	public final int id;
	public final String name;
	public final String owner;
	public final int itemtype;
	public final String displayitemtype;
	public final String icon;
	public final int level;
	public final int quality;
	public final int prize;
	public final cfg.cmd.condition.Gender gender;
	public final cfg.cmd.condition.ProfessionLimit professionlimit;
	public final cfg.cmd.action.BindType bindtype;
	public final String introduction;
	public final cfg.cmd.condition.MinMaxLevel levellimit;
	public final int maxpile;
	public final boolean batch;
	public final boolean cansell;
	public final cfg.cmd.condition.DayLimit daylimit;
	public ItemBasic(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.name = fs.getString();
		this.owner = fs.getString();
		this.itemtype = fs.getInt();
		this.displayitemtype = fs.getString();
		this.icon = fs.getString();
		this.level = fs.getInt();
		this.quality = fs.getInt();
		this.prize = fs.getInt();
		this.gender = new cfg.cmd.condition.Gender(fs);
		this.professionlimit = new cfg.cmd.condition.ProfessionLimit(fs);
		this.bindtype = new cfg.cmd.action.BindType(fs);
		this.introduction = fs.getString();
		this.levellimit = new cfg.cmd.condition.MinMaxLevel(fs);
		this.maxpile = fs.getInt();
		this.batch = fs.getBool();
		this.cansell = fs.getBool();
		this.daylimit = new cfg.cmd.condition.DayLimit(fs);
	}
}