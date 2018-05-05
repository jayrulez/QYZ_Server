package cfg.cmd.condition;
public final class MonsterHp extends cfg.cmd.condition.Condition {
	public final static int TYPEID = -781868271;
	final public int getTypeId() { return TYPEID; }
	public final int monsterid;
	public final int hp;
	public MonsterHp(cfg.DataStream fs) {
		super(fs);
		this.monsterid = fs.getInt();
		this.hp = fs.getInt();
	}
}