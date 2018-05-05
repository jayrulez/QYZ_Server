package cfg.ectype;
public final class Exit extends cfg.ectype.Action {
	public final static int TYPEID = 1772769802;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final boolean isopen;
	public Exit(cfg.DataStream fs) {
		super(fs);
		this.id = fs.getInt();
		this.isopen = fs.getBool();
	}
}