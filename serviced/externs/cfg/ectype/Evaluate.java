package cfg.ectype;
public final class Evaluate extends cfg.CfgObject {
	public final static int TYPEID = -1861161083;
	final public int getTypeId() { return TYPEID; }
	public final int evaluateid;
	public final String evaluatename;
	public final int conditionid;
	public final String effectid;
	public Evaluate(cfg.DataStream fs) {
		this.evaluateid = fs.getInt();
		this.evaluatename = fs.getString();
		this.conditionid = fs.getInt();
		this.effectid = fs.getString();
	}
}