package cfg.ectype;
public final class ExpMonsterRef extends cfg.CfgObject {
	public final static int TYPEID = 1028753642;
	final public int getTypeId() { return TYPEID; }
	public final int mapid;
	public final java.util.List<cfg.ectype.PointInfo> refreshopint = new java.util.ArrayList<>();
	public ExpMonsterRef(cfg.DataStream fs) {
		this.mapid = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.refreshopint.add(new cfg.ectype.PointInfo(fs));
		}
	}
}