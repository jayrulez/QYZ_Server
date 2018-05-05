package cfg.skill;
public final class Heal extends cfg.skill.HitPointAction {
	public final static int TYPEID = -1751796913;
	final public int getTypeId() { return TYPEID; }
	public final cfg.skill.AttackAction action;
	public Heal(cfg.DataStream fs) {
		super(fs);
		this.action = new cfg.skill.AttackAction(fs);
	}
}