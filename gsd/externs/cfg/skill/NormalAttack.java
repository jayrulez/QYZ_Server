package cfg.skill;
public final class NormalAttack extends cfg.skill.HitPointAction {
	public final static int TYPEID = -1132094346;
	final public int getTypeId() { return TYPEID; }
	public final cfg.skill.AttackAction action;
	public NormalAttack(cfg.DataStream fs) {
		super(fs);
		this.action = new cfg.skill.AttackAction(fs);
	}
}