package cfg.cmd.condition;
public final class PassEctype extends cfg.cmd.condition.Condition {
	public final static int TYPEID = -746287718;
	final public int getTypeId() { return TYPEID; }
	public final int ectypeid;
	public PassEctype(cfg.DataStream fs) {
		super(fs);
		this.ectypeid = fs.getInt();
	}
}