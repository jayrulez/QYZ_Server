package cfg.ectype;
public abstract class Rune extends cfg.CfgObject {
	public final int id;
	public final String model;
	public final String icon;
	public Rune(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.model = fs.getString();
		this.icon = fs.getString();
	}
}