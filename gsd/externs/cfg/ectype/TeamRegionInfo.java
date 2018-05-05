package cfg.ectype;
public final class TeamRegionInfo extends cfg.CfgObject {
	public final static int TYPEID = 670717419;
	final public int getTypeId() { return TYPEID; }
	public final int bornregion;
	public final cfg.map.Vector2 bornposition;
	public final java.util.List<cfg.ectype.SpeedRegionInfo> speedregioninfo = new java.util.ArrayList<>();
	public TeamRegionInfo(cfg.DataStream fs) {
		this.bornregion = fs.getInt();
		this.bornposition = new cfg.map.Vector2(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.speedregioninfo.add(new cfg.ectype.SpeedRegionInfo(fs));
		}
	}
}