package cfg.ectype;
public final class FamilyTeamMonsterWave extends cfg.CfgObject {
	public final static int TYPEID = -321220634;
	final public int getTypeId() { return TYPEID; }
	public final int regionid;
	public final int monster;
	public final int num;
	public FamilyTeamMonsterWave(cfg.DataStream fs) {
		this.regionid = fs.getInt();
		this.monster = fs.getInt();
		this.num = fs.getInt();
	}
}