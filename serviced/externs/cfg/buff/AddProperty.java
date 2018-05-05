package cfg.buff;
public final class AddProperty extends cfg.buff.Effect {
	public final static int TYPEID = -596439899;
	final public int getTypeId() { return TYPEID; }
	public final String introduction;
	public final int property;
	public final float value;
	public final int maxoverlay;
	public AddProperty(cfg.DataStream fs) {
		super(fs);
		this.introduction = fs.getString();
		this.property = fs.getInt();
		this.value = fs.getFloat();
		this.maxoverlay = fs.getInt();
	}
}