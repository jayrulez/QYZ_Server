package cfg.ectype;
public final class FreshGuide extends cfg.ectype.Action {
	public final static int TYPEID = 771642970;
	final public int getTypeId() { return TYPEID; }
	public final int guideid;
	public FreshGuide(cfg.DataStream fs) {
		super(fs);
		this.guideid = fs.getInt();
	}
}