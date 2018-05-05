package cfg.operationalactivity;
public final class AccessoryQuality extends cfg.operationalactivity.ActivityCondition {
	public final static int TYPEID = 1729839716;
	final public int getTypeId() { return TYPEID; }
	public final int level;
	public final int qulity;
	public final int num;
	public AccessoryQuality(cfg.DataStream fs) {
		super(fs);
		this.level = fs.getInt();
		this.qulity = fs.getInt();
		this.num = fs.getInt();
	}
}