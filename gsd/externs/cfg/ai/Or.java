package cfg.ai;
public final class Or extends cfg.ai.BoolExpr {
	public final static int TYPEID = 1456925567;
	final public int getTypeId() { return TYPEID; }
	public final cfg.ai.BoolExpr condition1;
	public final cfg.ai.BoolExpr condition2;
	public Or(cfg.DataStream fs) {
		super(fs);
		this.condition1 = (cfg.ai.BoolExpr)fs.getObject(fs.getString());
		this.condition2 = (cfg.ai.BoolExpr)fs.getObject(fs.getString());
	}
}