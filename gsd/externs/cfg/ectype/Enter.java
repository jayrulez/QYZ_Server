package cfg.ectype;
public final class Enter extends cfg.ectype.Action {
	public final static int TYPEID = -878998676;
	final public int getTypeId() { return TYPEID; }
	public final boolean isopen;
	public final int id;
	public Enter(cfg.DataStream fs) {
		super(fs);
		this.isopen = fs.getBool();
		this.id = fs.getInt();
	}
}