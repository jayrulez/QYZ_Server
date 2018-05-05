package cfg.ectype;
public final class DamageValue extends cfg.ectype.ExeCondition {
	public final static int TYPEID = -2147243818;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String op;
	public final String value;
	public DamageValue(cfg.DataStream fs) {
		super(fs);
		this.id = fs.getInt();
		this.op = fs.getString();
		this.value = fs.getString();
	}
}