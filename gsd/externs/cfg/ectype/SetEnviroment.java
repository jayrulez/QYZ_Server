package cfg.ectype;
public final class SetEnviroment extends cfg.ectype.Action {
	public final static int TYPEID = -25443379;
	final public int getTypeId() { return TYPEID; }
	public final int name;
	public final int value;
	public SetEnviroment(cfg.DataStream fs) {
		super(fs);
		this.name = fs.getInt();
		this.value = fs.getInt();
	}
}