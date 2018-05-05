package cfg.buff;
public final class AddMPInterval extends cfg.buff.IntervalEffect {
	public final static int TYPEID = 1100406328;
	final public int getTypeId() { return TYPEID; }
	public final String introduction;
	public final float value;
	public AddMPInterval(cfg.DataStream fs) {
		super(fs);
		this.introduction = fs.getString();
		this.value = fs.getFloat();
	}
}