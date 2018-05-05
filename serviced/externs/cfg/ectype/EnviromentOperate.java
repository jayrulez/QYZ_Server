package cfg.ectype;
public final class EnviromentOperate extends cfg.ectype.Action {
	public final static int TYPEID = 903339745;
	final public int getTypeId() { return TYPEID; }
	public final int name;
	public final int op;
	public final int value;
	public EnviromentOperate(cfg.DataStream fs) {
		super(fs);
		this.name = fs.getInt();
		this.op = fs.getInt();
		this.value = fs.getInt();
	}
}