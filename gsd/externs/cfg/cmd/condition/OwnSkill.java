package cfg.cmd.condition;
public final class OwnSkill extends cfg.cmd.condition.Condition {
	public final static int TYPEID = 1663582204;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<Integer> skillids = new java.util.ArrayList<>();
	public OwnSkill(cfg.DataStream fs) {
		super(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.skillids.add(fs.getInt());
		}
	}
}