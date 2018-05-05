package cfg.ai;
public final class Not extends cfg.ai.BoolExpr {
	public final static int TYPEID = -2079948617;
	final public int getTypeId() { return TYPEID; }
	public final cfg.ai.BoolExpr condition;
	public Not(cfg.DataStream fs) {
		super(fs);
		this.condition = (cfg.ai.BoolExpr)fs.getObject(fs.getString());
	}
}