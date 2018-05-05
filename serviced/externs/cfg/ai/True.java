package cfg.ai;
public final class True extends cfg.ai.BoolExpr {
	public final static int TYPEID = -53715926;
	final public int getTypeId() { return TYPEID; }
	public True(cfg.DataStream fs) {
		super(fs);
	}
}