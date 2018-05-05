package cfg.ai;
public final class Switch extends cfg.ai.Expression {
	public final static int TYPEID = -105748784;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<cfg.ai.Case> cases = new java.util.ArrayList<>();
	public Switch(cfg.DataStream fs) {
		super(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.cases.add(new cfg.ai.Case(fs));
		}
	}
}