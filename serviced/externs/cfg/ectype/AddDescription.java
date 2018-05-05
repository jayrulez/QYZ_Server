package cfg.ectype;
public final class AddDescription extends cfg.ectype.Action {
	public final static int TYPEID = 606934663;
	final public int getTypeId() { return TYPEID; }
	public final String content;
	public AddDescription(cfg.DataStream fs) {
		super(fs);
		this.content = fs.getString();
	}
}