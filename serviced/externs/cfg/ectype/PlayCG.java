package cfg.ectype;
public final class PlayCG extends cfg.ectype.Action {
	public final static int TYPEID = -1166629756;
	final public int getTypeId() { return TYPEID; }
	public final String name;
	public final boolean canskip;
	public PlayCG(cfg.DataStream fs) {
		super(fs);
		this.name = fs.getString();
		this.canskip = fs.getBool();
	}
}