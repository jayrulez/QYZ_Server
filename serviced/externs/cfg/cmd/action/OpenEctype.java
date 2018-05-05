package cfg.cmd.action;
public final class OpenEctype extends cfg.cmd.action.Action {
	public final static int TYPEID = -917873444;
	final public int getTypeId() { return TYPEID; }
	public final int ectypeid;
	public OpenEctype(cfg.DataStream fs) {
		super(fs);
		this.ectypeid = fs.getInt();
	}
}