package cfg.ai;
public final class While extends cfg.ai.Expression {
	public final static int TYPEID = -1662732267;
	final public int getTypeId() { return TYPEID; }
	public final cfg.ai.BoolExpr condition;
	public final java.util.List<cfg.ai.Expression> exprs = new java.util.ArrayList<>();
	public While(cfg.DataStream fs) {
		super(fs);
		this.condition = (cfg.ai.BoolExpr)fs.getObject(fs.getString());
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.exprs.add((cfg.ai.Expression)fs.getObject(fs.getString()));
		}
	}
}