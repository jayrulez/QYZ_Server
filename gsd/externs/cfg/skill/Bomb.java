package cfg.skill;
public final class Bomb extends cfg.skill.TraceObject {
	public final static int TYPEID = -1751965687;
	final public int getTypeId() { return TYPEID; }
	public final int attacklistid;
	public Bomb(cfg.DataStream fs) {
		super(fs);
		this.attacklistid = fs.getInt();
	}
}