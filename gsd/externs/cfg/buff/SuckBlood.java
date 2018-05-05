package cfg.buff;
public final class SuckBlood extends cfg.buff.Effect {
	public final static int TYPEID = -1935235937;
	final public int getTypeId() { return TYPEID; }
	public final String introduction;
	public final java.util.List<Float> suckrate = new java.util.ArrayList<>();
	public SuckBlood(cfg.DataStream fs) {
		super(fs);
		this.introduction = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.suckrate.add(fs.getFloat());
		}
	}
}