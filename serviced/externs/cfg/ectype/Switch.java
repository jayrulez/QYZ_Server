package cfg.ectype;
public final class Switch extends cfg.ectype.Action {
	public final static int TYPEID = -1070349024;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<cfg.ectype.Case> cases = new java.util.ArrayList<>();
	public Switch(cfg.DataStream fs) {
		super(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.cases.add(new cfg.ectype.Case(fs));
		}
	}
}