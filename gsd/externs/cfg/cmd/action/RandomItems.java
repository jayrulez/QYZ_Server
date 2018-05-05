package cfg.cmd.action;
public final class RandomItems extends cfg.cmd.action.Bonus {
	public final static int TYPEID = -1301042461;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<Integer> items = new java.util.ArrayList<>();
	public RandomItems(cfg.DataStream fs) {
		super(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.items.add(fs.getInt());
		}
	}
}