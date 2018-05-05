package cfg.pureair;
public final class LuckyValue extends cfg.CfgObject {
	public final static int TYPEID = -249482897;
	final public int getTypeId() { return TYPEID; }
	public static final int LUCKYMAXVALUE = 1000;
	public static final int OPEN_LEVEL = 60;
	public static final int LEVEL_LIMIT = 30;
	public static final int AWAKE_LIMIT = 15;
	public static final int STAR_LIMIT = 5;
	public final java.util.List<Integer> up = new java.util.ArrayList<>();
	public LuckyValue(cfg.DataStream fs) {
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.up.add(fs.getInt());
		}
	}
}