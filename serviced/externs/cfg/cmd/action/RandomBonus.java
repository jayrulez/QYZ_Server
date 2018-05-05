package cfg.cmd.action;
public final class RandomBonus extends cfg.cmd.action.Bonus {
	public final static int TYPEID = -1307647166;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<cfg.cmd.action.BonusRandomInfo> bonuss = new java.util.ArrayList<>();
	public RandomBonus(cfg.DataStream fs) {
		super(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.bonuss.add(new cfg.cmd.action.BonusRandomInfo(fs));
		}
	}
}