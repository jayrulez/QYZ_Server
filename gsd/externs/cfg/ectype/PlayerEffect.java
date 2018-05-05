package cfg.ectype;
public final class PlayerEffect extends cfg.ectype.Action {
	public final static int TYPEID = 2025963710;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public PlayerEffect(cfg.DataStream fs) {
		super(fs);
		this.id = fs.getInt();
	}
}