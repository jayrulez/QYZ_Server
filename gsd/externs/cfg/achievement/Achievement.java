package cfg.achievement;
public abstract class Achievement extends cfg.CfgObject {
	public final int id;
	public final int achievementtype;
	public final String achievementtypename;
	public final int type;
	public final long value;
	public final boolean isamount;
	public final String name;
	public final String detail;
	public Achievement(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.achievementtype = fs.getInt();
		this.achievementtypename = fs.getString();
		this.type = fs.getInt();
		this.value = fs.getLong();
		this.isamount = fs.getBool();
		this.name = fs.getString();
		this.detail = fs.getString();
	}
}