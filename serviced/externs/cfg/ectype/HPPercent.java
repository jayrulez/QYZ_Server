package cfg.ectype;
public final class HPPercent extends cfg.ectype.ExeCondition {
	public final static int TYPEID = 1393046737;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String op;
	public final float percent;
	public HPPercent(cfg.DataStream fs) {
		super(fs);
		this.id = fs.getInt();
		this.op = fs.getString();
		this.percent = fs.getFloat();
	}
}