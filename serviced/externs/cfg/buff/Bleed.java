package cfg.buff;
public final class Bleed extends cfg.buff.IntervalEffect {
	public final static int TYPEID = 1270241673;
	final public int getTypeId() { return TYPEID; }
	public final String introduction;
	public final float value;
	public Bleed(cfg.DataStream fs) {
		super(fs);
		this.introduction = fs.getString();
		this.value = fs.getFloat();
	}
}