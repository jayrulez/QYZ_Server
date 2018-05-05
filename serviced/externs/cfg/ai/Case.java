package cfg.ai;
public final class Case extends cfg.ai.Expression {
	public final static int TYPEID = -54238772;
	final public int getTypeId() { return TYPEID; }
	public final cfg.ai.BoolExpr condition;
	public final cfg.ai.Expression expression;
	public Case(cfg.DataStream fs) {
		super(fs);
		this.condition = (cfg.ai.BoolExpr)fs.getObject(fs.getString());
		this.expression = (cfg.ai.Expression)fs.getObject(fs.getString());
	}
}