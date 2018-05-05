package cfg.ectype;
public final class ConditionTimer extends cfg.ectype.ExeCondition {
	public final static int TYPEID = 1309148374;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public ConditionTimer(cfg.DataStream fs) {
		super(fs);
		this.id = fs.getInt();
	}
}