package cfg.ectype;
public final class Sequence extends cfg.ectype.Action {
	public final static int TYPEID = -673400147;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<cfg.ectype.Action> actions = new java.util.ArrayList<>();
	public Sequence(cfg.DataStream fs) {
		super(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.actions.add((cfg.ectype.Action)fs.getObject(fs.getString()));
		}
	}
}