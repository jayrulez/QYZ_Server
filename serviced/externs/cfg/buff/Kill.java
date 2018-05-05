package cfg.buff;
public final class Kill extends cfg.buff.Effect {
	public final static int TYPEID = 456882991;
	final public int getTypeId() { return TYPEID; }
	public final float probability;
	public final float hppercent;
	public Kill(cfg.DataStream fs) {
		super(fs);
		this.probability = fs.getFloat();
		this.hppercent = fs.getFloat();
	}
}