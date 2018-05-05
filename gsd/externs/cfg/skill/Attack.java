package cfg.skill;
public final class Attack extends cfg.skill.Action {
	public final static int TYPEID = -35645969;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final int hitzoneid;
	public final int beattackeffectid;
	public Attack(cfg.DataStream fs) {
		super(fs);
		this.id = fs.getInt();
		this.hitzoneid = fs.getInt();
		this.beattackeffectid = fs.getInt();
	}
}