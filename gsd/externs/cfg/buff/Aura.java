package cfg.buff;
public final class Aura extends cfg.buff.Effect {
	public final static int TYPEID = 456596788;
	final public int getTypeId() { return TYPEID; }
	public final int buffid;
	public final float judgeinterval;
	public final float judgeradius;
	public Aura(cfg.DataStream fs) {
		super(fs);
		this.buffid = fs.getInt();
		this.judgeinterval = fs.getFloat();
		this.judgeradius = fs.getFloat();
	}
}