package cfg.buff;
public final class LacerationbyLevel extends cfg.buff.IntervalEffect {
	public final static int TYPEID = -60673310;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<String> introduction = new java.util.ArrayList<>();
	public final java.util.List<Float> rate = new java.util.ArrayList<>();
	public final java.util.List<Float> damage = new java.util.ArrayList<>();
	public final boolean ignoredefence;
	public LacerationbyLevel(cfg.DataStream fs) {
		super(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.introduction.add(fs.getString());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.rate.add(fs.getFloat());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.damage.add(fs.getFloat());
		}
		this.ignoredefence = fs.getBool();
	}
}