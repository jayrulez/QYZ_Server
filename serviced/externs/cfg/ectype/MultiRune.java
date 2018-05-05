package cfg.ectype;
public final class MultiRune extends cfg.ectype.Rune {
	public final static int TYPEID = 506115751;
	final public int getTypeId() { return TYPEID; }
	public final cfg.cmd.action.MultiBonus items;
	public MultiRune(cfg.DataStream fs) {
		super(fs);
		this.items = new cfg.cmd.action.MultiBonus(fs);
	}
}