package cfg.cmd.condition;
public final class OpenEctype extends cfg.cmd.condition.Condition {
	public final static int TYPEID = -663879405;
	final public int getTypeId() { return TYPEID; }
	public final int ectypeid;
	public OpenEctype(cfg.DataStream fs) {
		super(fs);
		this.ectypeid = fs.getInt();
	}
}