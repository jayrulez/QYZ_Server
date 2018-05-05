package cfg.ectype;
public final class SpecialEffect extends cfg.ectype.Action {
	public final static int TYPEID = 552233022;
	final public int getTypeId() { return TYPEID; }
	public final String path;
	public final boolean isopen;
	public SpecialEffect(cfg.DataStream fs) {
		super(fs);
		this.path = fs.getString();
		this.isopen = fs.getBool();
	}
}