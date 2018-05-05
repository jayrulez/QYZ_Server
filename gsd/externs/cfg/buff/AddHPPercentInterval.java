package cfg.buff;
public final class AddHPPercentInterval extends cfg.buff.IntervalEffect {
	public final static int TYPEID = 240472402;
	final public int getTypeId() { return TYPEID; }
	public final String introduction;
	public final float value;
	public AddHPPercentInterval(cfg.DataStream fs) {
		super(fs);
		this.introduction = fs.getString();
		this.value = fs.getFloat();
	}
}