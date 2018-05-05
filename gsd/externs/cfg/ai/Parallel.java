package cfg.ai;
public final class Parallel extends cfg.ai.Expression {
	public final static int TYPEID = -1583651165;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<cfg.ai.Expression> exprs = new java.util.ArrayList<>();
	public Parallel(cfg.DataStream fs) {
		super(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.exprs.add((cfg.ai.Expression)fs.getObject(fs.getString()));
		}
	}
}