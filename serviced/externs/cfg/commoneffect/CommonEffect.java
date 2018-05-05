package cfg.commoneffect;
public final class CommonEffect extends cfg.CfgObject {
	public final static int TYPEID = 365103076;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final java.util.List<cfg.skill.Action> actions = new java.util.ArrayList<>();
	public CommonEffect(cfg.DataStream fs) {
		this.id = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.actions.add((cfg.skill.Action)fs.getObject(fs.getString()));
		}
	}
}