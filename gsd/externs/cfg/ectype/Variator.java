package cfg.ectype;
public final class Variator extends cfg.ectype.ExeCondition {
	public final static int TYPEID = 1022450020;
	final public int getTypeId() { return TYPEID; }
	public final int enviroment;
	public final int value;
	public Variator(cfg.DataStream fs) {
		super(fs);
		this.enviroment = fs.getInt();
		this.value = fs.getInt();
	}
}