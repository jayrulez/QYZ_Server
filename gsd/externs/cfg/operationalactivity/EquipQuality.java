package cfg.operationalactivity;
public final class EquipQuality extends cfg.operationalactivity.ActivityCondition {
	public final static int TYPEID = -516206842;
	final public int getTypeId() { return TYPEID; }
	public final int level;
	public final int qulity;
	public final int num;
	public EquipQuality(cfg.DataStream fs) {
		super(fs);
		this.level = fs.getInt();
		this.qulity = fs.getInt();
		this.num = fs.getInt();
	}
}