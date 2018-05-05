package cfg.ectype;
public final class SurviveTime extends cfg.ectype.ExeCondition {
	public final static int TYPEID = 1100093043;
	final public int getTypeId() { return TYPEID; }
	public final int time;
	public SurviveTime(cfg.DataStream fs) {
		super(fs);
		this.time = fs.getInt();
	}
}