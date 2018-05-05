package cfg.operationalactivity;
public final class PetQuality extends cfg.operationalactivity.ActivityCondition {
	public final static int TYPEID = -1538976521;
	final public int getTypeId() { return TYPEID; }
	public final int qulity;
	public final int num;
	public PetQuality(cfg.DataStream fs) {
		super(fs);
		this.qulity = fs.getInt();
		this.num = fs.getInt();
	}
}