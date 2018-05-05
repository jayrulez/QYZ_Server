package cfg.operationalactivity;
public abstract class ActivityCondition extends cfg.CfgObject {
	public final int conditiontype;
	public final String description1;
	public final String description2;
	public ActivityCondition(cfg.DataStream fs) {
		this.conditiontype = fs.getInt();
		this.description1 = fs.getString();
		this.description2 = fs.getString();
	}
}