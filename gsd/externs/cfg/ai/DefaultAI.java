package cfg.ai;
public final class DefaultAI extends cfg.ai.AI {
	public final static int TYPEID = -1009439475;
	final public int getTypeId() { return TYPEID; }
	public final boolean walkback;
	public DefaultAI(cfg.DataStream fs) {
		super(fs);
		this.walkback = fs.getBool();
	}
}