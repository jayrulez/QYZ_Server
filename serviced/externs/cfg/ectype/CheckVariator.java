package cfg.ectype;
public final class CheckVariator extends cfg.ectype.ExeCondition {
	public final static int TYPEID = -1273591756;
	final public int getTypeId() { return TYPEID; }
	public final int name;
	public final int op;
	public final int value;
	public CheckVariator(cfg.DataStream fs) {
		super(fs);
		this.name = fs.getInt();
		this.op = fs.getInt();
		this.value = fs.getInt();
	}
}