package cfg.buff;
public final class BleedPercent extends cfg.buff.IntervalEffect {
	public final static int TYPEID = 1449494140;
	final public int getTypeId() { return TYPEID; }
	public final String introduction;
	public final float value;
	public BleedPercent(cfg.DataStream fs) {
		super(fs);
		this.introduction = fs.getString();
		this.value = fs.getFloat();
	}
}