package cfg.ectype;
public final class RandomRune extends cfg.ectype.Rune {
	public final static int TYPEID = 1682640521;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<cfg.ectype.RuneRandomInfo> runes = new java.util.ArrayList<>();
	public RandomRune(cfg.DataStream fs) {
		super(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.runes.add(new cfg.ectype.RuneRandomInfo(fs));
		}
	}
}