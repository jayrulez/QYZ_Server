package cfg.ectype;
public final class FamilyTeamLevelMonserInfo extends cfg.CfgObject {
	public final static int TYPEID = 1575839673;
	final public int getTypeId() { return TYPEID; }
	public final int level;
	public final java.util.List<cfg.ectype.FamilyTeamMonsterWave> monsterwaves = new java.util.ArrayList<>();
	public FamilyTeamLevelMonserInfo(cfg.DataStream fs) {
		this.level = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.monsterwaves.add(new cfg.ectype.FamilyTeamMonsterWave(fs));
		}
	}
}