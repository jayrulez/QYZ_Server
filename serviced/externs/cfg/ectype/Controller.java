package cfg.ectype;
public final class Controller extends cfg.ectype.ExeCondition {
	public final static int TYPEID = -2090493048;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public Controller(cfg.DataStream fs) {
		super(fs);
		this.id = fs.getInt();
	}
}