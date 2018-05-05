package cfg.ai;
public final class ChooseDefencer extends cfg.ai.ChooseTargetPolicy {
	public final static int TYPEID = -1007737611;
	final public int getTypeId() { return TYPEID; }
	public ChooseDefencer(cfg.DataStream fs) {
		super(fs);
	}
}