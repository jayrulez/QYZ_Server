package cfg.skill;
public final class AttackList extends cfg.CfgObject {
	public final static int TYPEID = 1125755693;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String name;
	public final java.util.List<cfg.skill.Attack> attacks = new java.util.ArrayList<>();
	public AttackList(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.name = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.attacks.add(new cfg.skill.Attack(fs));
		}
	}
}