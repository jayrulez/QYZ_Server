package cfg.ai;
public final class Print extends cfg.ai.Expression {
	public final static int TYPEID = -1668898927;
	final public int getTypeId() { return TYPEID; }
	public final String text;
	public Print(cfg.DataStream fs) {
		super(fs);
		this.text = fs.getString();
	}
}