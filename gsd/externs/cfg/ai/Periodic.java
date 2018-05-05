package cfg.ai;
public final class Periodic extends cfg.ai.Expression {
	public final static int TYPEID = 1973833527;
	final public int getTypeId() { return TYPEID; }
	public final float interval;
	public final cfg.ai.Expression expression;
	public Periodic(cfg.DataStream fs) {
		super(fs);
		this.interval = fs.getFloat();
		this.expression = (cfg.ai.Expression)fs.getObject(fs.getString());
	}
}