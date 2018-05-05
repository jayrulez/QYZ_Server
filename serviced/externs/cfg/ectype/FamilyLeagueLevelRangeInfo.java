package cfg.ectype;
public final class FamilyLeagueLevelRangeInfo extends cfg.CfgObject {
	public final static int TYPEID = -72394266;
	final public int getTypeId() { return TYPEID; }
	public final int minlevel;
	public final java.util.List<Integer> towerids = new java.util.ArrayList<>();
	public FamilyLeagueLevelRangeInfo(cfg.DataStream fs) {
		this.minlevel = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.towerids.add(fs.getInt());
		}
	}
}