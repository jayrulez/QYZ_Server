package cfg.buff;
public final class AddMPByLevelInterval extends cfg.buff.IntervalEffect {
	public final static int TYPEID = -1352509313;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<String> introduction = new java.util.ArrayList<>();
	public final java.util.List<Float> value = new java.util.ArrayList<>();
	public AddMPByLevelInterval(cfg.DataStream fs) {
		super(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.introduction.add(fs.getString());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.value.add(fs.getFloat());
		}
	}
}