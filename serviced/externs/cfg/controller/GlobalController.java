package cfg.controller;
public abstract class GlobalController extends cfg.CfgObject {
	public final int id;
	public final String name;
	public final String introcuction;
	public final String owner;
	public final float duration;
	public final int waitbeforeopen;
	public final int waitbeforeclose;
	public final cfg.controller.Activity activity;
	public GlobalController(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.name = fs.getString();
		this.introcuction = fs.getString();
		this.owner = fs.getString();
		this.duration = fs.getFloat();
		this.waitbeforeopen = fs.getInt();
		this.waitbeforeclose = fs.getInt();
		this.activity = (cfg.controller.Activity)fs.getObject(fs.getString());
	}
}