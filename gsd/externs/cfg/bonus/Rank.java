package cfg.bonus;
public final class Rank extends cfg.CfgObject {
	public final static int TYPEID = -1623503739;
	final public int getTypeId() { return TYPEID; }
	public final int ranktype;
	public final boolean isrolerank;
	public final int showsize;
	public final int ranksize;
	public final int updatetype;
	public final boolean realtime;
	public final boolean desc;
	public final int refreshrate;
	public Rank(cfg.DataStream fs) {
		this.ranktype = fs.getInt();
		this.isrolerank = fs.getBool();
		this.showsize = fs.getInt();
		this.ranksize = fs.getInt();
		this.updatetype = fs.getInt();
		this.realtime = fs.getBool();
		this.desc = fs.getBool();
		this.refreshrate = fs.getInt();
	}
}