package cfg.skill;
public final class BuffAttack extends cfg.skill.HitPointAction {
	public final static int TYPEID = -329098078;
	final public int getTypeId() { return TYPEID; }
	public final int buffid;
	public BuffAttack(cfg.DataStream fs) {
		super(fs);
		this.buffid = fs.getInt();
	}
}