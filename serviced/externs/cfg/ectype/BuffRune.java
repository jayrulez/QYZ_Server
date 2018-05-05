package cfg.ectype;
public final class BuffRune extends cfg.ectype.Rune {
	public final static int TYPEID = 749419801;
	final public int getTypeId() { return TYPEID; }
	public final int buffid;
	public BuffRune(cfg.DataStream fs) {
		super(fs);
		this.buffid = fs.getInt();
	}
}