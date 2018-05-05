package cfg.cmd.action;
public final class AddBuff extends cfg.cmd.action.Action {
	public final static int TYPEID = -1694818886;
	final public int getTypeId() { return TYPEID; }
	public final float buffid;
	public AddBuff(cfg.DataStream fs) {
		super(fs);
		this.buffid = fs.getFloat();
	}
}