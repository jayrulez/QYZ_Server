package cfg.map;
public final class IndexedCircleRegion extends cfg.CfgObject {
	public final static int TYPEID = -1944150983;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final boolean allowpk;
	public final boolean allowride;
	public final boolean allowtrade;
	public final cfg.map.CircleRegion circle;
	public IndexedCircleRegion(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.allowpk = fs.getBool();
		this.allowride = fs.getBool();
		this.allowtrade = fs.getBool();
		this.circle = new cfg.map.CircleRegion(fs);
	}
}