package cfg.cmd.action;
public final class BonusRandomInfo extends cfg.CfgObject {
	public final static int TYPEID = -1114842954;
	final public int getTypeId() { return TYPEID; }
	public final cfg.cmd.action.Bonus bonus;
	public final float droprate;
	public BonusRandomInfo(cfg.DataStream fs) {
		this.bonus = (cfg.cmd.action.Bonus)fs.getObject(fs.getString());
		this.droprate = fs.getFloat();
	}
}