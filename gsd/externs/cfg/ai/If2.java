package cfg.ai;
public final class If2 extends cfg.ai.Expression {
	public final static int TYPEID = -2079953767;
	final public int getTypeId() { return TYPEID; }
	public final cfg.ai.BoolExpr condition;
	public final cfg.ai.Expression iftrue;
	public final cfg.ai.Expression iffalse;
	public If2(cfg.DataStream fs) {
		super(fs);
		this.condition = (cfg.ai.BoolExpr)fs.getObject(fs.getString());
		this.iftrue = (cfg.ai.Expression)fs.getObject(fs.getString());
		this.iffalse = (cfg.ai.Expression)fs.getObject(fs.getString());
	}
}