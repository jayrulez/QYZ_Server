package cfg.ectype;
public final class Delay extends cfg.ectype.Action {
	public final static int TYPEID = -880198121;
	final public int getTypeId() { return TYPEID; }
	public final float time;
	public Delay(cfg.DataStream fs) {
		super(fs);
		this.time = fs.getFloat();
	}
}