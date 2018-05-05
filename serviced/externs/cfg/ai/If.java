package cfg.ai;
public final class If extends cfg.ai.Expression {
	public final static int TYPEID = 1456925369;
	final public int getTypeId() { return TYPEID; }
	public final cfg.ai.BoolExpr condition;
	public final cfg.ai.Expression iftrue;
	public If(cfg.DataStream fs) {
		super(fs);
		this.condition = (cfg.ai.BoolExpr)fs.getObject(fs.getString());
		this.iftrue = (cfg.ai.Expression)fs.getObject(fs.getString());
	}
}