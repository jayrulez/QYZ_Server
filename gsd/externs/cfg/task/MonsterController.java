package cfg.task;
public final class MonsterController extends cfg.CfgObject {
	public final static int TYPEID = 1474262935;
	final public int getTypeId() { return TYPEID; }
	public final int controllerid;
	public final boolean trigger;
	public MonsterController(cfg.DataStream fs) {
		this.controllerid = fs.getInt();
		this.trigger = fs.getBool();
	}
}