package cfg.manual;
public final class PetStageStarBonus extends cfg.CfgObject {
	public final static int TYPEID = -1410059120;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String name;
	public final java.util.List<cfg.manual.StageStarData> propertylist = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.manual.StageStarData> propertylist_stagestarlevel= new java.util.HashMap<>();
	public PetStageStarBonus(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.name = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.manual.StageStarData _v = new cfg.manual.StageStarData(fs);
			this.propertylist.add(_v);
			this.propertylist_stagestarlevel.put(_v.stagestarlevel, _v);
		}
	}
}