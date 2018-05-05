package cfg.cmd.condition;
public final class Equiped extends cfg.cmd.condition.Condition {
	public final static int TYPEID = 1127932190;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<Integer> id = new java.util.ArrayList<>();
	public Equiped(cfg.DataStream fs) {
		super(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.id.add(fs.getInt());
		}
	}
}