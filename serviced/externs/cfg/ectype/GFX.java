package cfg.ectype;
public final class GFX extends cfg.ectype.Action {
	public final static int TYPEID = -912644851;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public GFX(cfg.DataStream fs) {
		super(fs);
		this.id = fs.getInt();
	}
}