package cfg.ai;
public final class Periodic2 extends cfg.ai.Expression {
	public final static int TYPEID = 1059297243;
	final public int getTypeId() { return TYPEID; }
	public final float interval;
	public final int maxrepeat;
	public final cfg.ai.Expression expression;
	public Periodic2(cfg.DataStream fs) {
		super(fs);
		this.interval = fs.getFloat();
		this.maxrepeat = fs.getInt();
		this.expression = (cfg.ai.Expression)fs.getObject(fs.getString());
	}
}