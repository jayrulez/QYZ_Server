package cfg.buff;
public final class SkillEffect extends cfg.buff.Effect {
	public final static int TYPEID = -1092406703;
	final public int getTypeId() { return TYPEID; }
	public final int skillid;
	public final java.util.List<Integer> hitpoint = new java.util.ArrayList<>();
	public final int targetamountchange;
	public final boolean isbefore;
	public final int buffid;
	public SkillEffect(cfg.DataStream fs) {
		super(fs);
		this.skillid = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.hitpoint.add(fs.getInt());
		}
		this.targetamountchange = fs.getInt();
		this.isbefore = fs.getBool();
		this.buffid = fs.getInt();
	}
}