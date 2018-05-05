package cfg.operationalactivity;
public final class Rank extends cfg.operationalactivity.ActivityCondition {
	public final static int TYPEID = 1885439875;
	final public int getTypeId() { return TYPEID; }
	public final int type;
	public final int ranking;
	public Rank(cfg.DataStream fs) {
		super(fs);
		this.type = fs.getInt();
		this.ranking = fs.getInt();
	}
}