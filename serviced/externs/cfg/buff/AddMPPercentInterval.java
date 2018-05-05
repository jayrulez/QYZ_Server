package cfg.buff;
public final class AddMPPercentInterval extends cfg.buff.IntervalEffect {
	public final static int TYPEID = -1582913705;
	final public int getTypeId() { return TYPEID; }
	public final String introduction;
	public final float value;
	public AddMPPercentInterval(cfg.DataStream fs) {
		super(fs);
		this.introduction = fs.getString();
		this.value = fs.getFloat();
	}
}