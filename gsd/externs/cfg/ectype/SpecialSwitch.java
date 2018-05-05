package cfg.ectype;
public final class SpecialSwitch extends cfg.ectype.ExeCondition {
	public final static int TYPEID = 968844769;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public SpecialSwitch(cfg.DataStream fs) {
		super(fs);
		this.id = fs.getInt();
	}
}