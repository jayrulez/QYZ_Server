package cfg.ai;
public final class ChooseNearest extends cfg.ai.ChooseTargetPolicy {
	public final static int TYPEID = 1910924075;
	final public int getTypeId() { return TYPEID; }
	public ChooseNearest(cfg.DataStream fs) {
		super(fs);
	}
}