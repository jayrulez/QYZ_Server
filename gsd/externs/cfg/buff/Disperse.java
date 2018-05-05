package cfg.buff;
public final class Disperse extends cfg.buff.Effect {
	public final static int TYPEID = 47054770;
	final public int getTypeId() { return TYPEID; }
	public final int dispersetype;
	public final int maxnum;
	public Disperse(cfg.DataStream fs) {
		super(fs);
		this.dispersetype = fs.getInt();
		this.maxnum = fs.getInt();
	}
}