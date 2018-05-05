package cfg.ectype;
public abstract class Action extends cfg.CfgObject {
	public final int actionid;
	public final boolean isglobal;
	public Action(cfg.DataStream fs) {
		this.actionid = fs.getInt();
		this.isglobal = fs.getBool();
	}
}