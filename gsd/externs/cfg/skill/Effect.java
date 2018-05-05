package cfg.skill;
public final class Effect extends cfg.CfgObject {
	public final static int TYPEID = 65528120;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String name;
	public final java.util.List<cfg.skill.Action> actions = new java.util.ArrayList<>();
	public Effect(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.name = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.actions.add((cfg.skill.Action)fs.getObject(fs.getString()));
		}
	}
}