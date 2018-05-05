package cfg.operationalactivity;
public final class TalismanQuality extends cfg.operationalactivity.ActivityCondition {
	public final static int TYPEID = -551330729;
	final public int getTypeId() { return TYPEID; }
	public final int qulity;
	public final int num;
	public TalismanQuality(cfg.DataStream fs) {
		super(fs);
		this.qulity = fs.getInt();
		this.num = fs.getInt();
	}
}