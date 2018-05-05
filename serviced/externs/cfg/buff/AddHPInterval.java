package cfg.buff;
public final class AddHPInterval extends cfg.buff.IntervalEffect {
	public final static int TYPEID = 2082973853;
	final public int getTypeId() { return TYPEID; }
	public final String introduction;
	public final float value;
	public AddHPInterval(cfg.DataStream fs) {
		super(fs);
		this.introduction = fs.getString();
		this.value = fs.getFloat();
	}
}