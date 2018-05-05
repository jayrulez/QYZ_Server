package cfg.buff;
public abstract class Effect extends cfg.CfgObject {
	public final int id;
	public final String name;
	public final int groupid;
	public final int priority;
	public final float hitrate;
	public final boolean showicon;
	public final boolean isharmful;
	public final boolean candisperse;
	public final String icontype;
	public final boolean isresist;
	public final int commoneffectid;
	public final int displaypriority;
	public final boolean ispersistent;
	public Effect(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.name = fs.getString();
		this.groupid = fs.getInt();
		this.priority = fs.getInt();
		this.hitrate = fs.getFloat();
		this.showicon = fs.getBool();
		this.isharmful = fs.getBool();
		this.candisperse = fs.getBool();
		this.icontype = fs.getString();
		this.isresist = fs.getBool();
		this.commoneffectid = fs.getInt();
		this.displaypriority = fs.getInt();
		this.ispersistent = fs.getBool();
	}
}