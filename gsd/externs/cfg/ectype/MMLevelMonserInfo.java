package cfg.ectype;
public final class MMLevelMonserInfo extends cfg.CfgObject {
	public final static int TYPEID = -697033670;
	final public int getTypeId() { return TYPEID; }
	public final int level;
	public final java.util.List<cfg.ectype.MMMonsterWave> monsterwaves = new java.util.ArrayList<>();
	public MMLevelMonserInfo(cfg.DataStream fs) {
		this.level = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.monsterwaves.add(new cfg.ectype.MMMonsterWave(fs));
		}
	}
}