package cfg.ai;
public final class Sequence extends cfg.ai.Expression {
	public final static int TYPEID = -1405505443;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<cfg.ai.Expression> exprs = new java.util.ArrayList<>();
	public Sequence(cfg.DataStream fs) {
		super(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.exprs.add((cfg.ai.Expression)fs.getObject(fs.getString()));
		}
	}
}