package cfg.operationalactivity;
public abstract class SimpleCondition extends cfg.operationalactivity.ActivityCondition {
	public final int num;
	public SimpleCondition(cfg.DataStream fs) {
		super(fs);
		this.num = fs.getInt();
	}
}