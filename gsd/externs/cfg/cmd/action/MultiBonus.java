package cfg.cmd.action;
public final class MultiBonus extends cfg.cmd.action.Bonus {
	public final static int TYPEID = 372654976;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<cfg.cmd.action.Bonus> bonuss = new java.util.ArrayList<>();
	public MultiBonus(cfg.DataStream fs) {
		super(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.bonuss.add((cfg.cmd.action.Bonus)fs.getObject(fs.getString()));
		}
	}
}