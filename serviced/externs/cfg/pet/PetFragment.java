package cfg.pet;
public final class PetFragment extends cfg.CfgObject {
	public final static int TYPEID = 1917638806;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String name;
	public final String icon;
	public final int level;
	public final int quality;
	public final int prize;
	public final cfg.cmd.condition.ProfessionLimit professionlimit;
	public final cfg.cmd.action.BindType bindtype;
	public final String introduction;
	public final int maxpile;
	public final boolean cansell;
	public final int petID;
	public final int number;
	public PetFragment(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.name = fs.getString();
		this.icon = fs.getString();
		this.level = fs.getInt();
		this.quality = fs.getInt();
		this.prize = fs.getInt();
		this.professionlimit = new cfg.cmd.condition.ProfessionLimit(fs);
		this.bindtype = new cfg.cmd.action.BindType(fs);
		this.introduction = fs.getString();
		this.maxpile = fs.getInt();
		this.cansell = fs.getBool();
		this.petID = fs.getInt();
		this.number = fs.getInt();
	}
}