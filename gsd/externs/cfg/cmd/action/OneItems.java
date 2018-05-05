package cfg.cmd.action;
public final class OneItems extends cfg.cmd.action.Bonus {
	public final static int TYPEID = -2050071404;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<Integer> items = new java.util.ArrayList<>();
	public OneItems(cfg.DataStream fs) {
		super(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.items.add(fs.getInt());
		}
	}
}