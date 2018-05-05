package cfg.cmd.action;
public final class Items extends cfg.cmd.action.Bonus {
	public final static int TYPEID = -954789274;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<cfg.cmd.action.Item> items = new java.util.ArrayList<>();
	public Items(cfg.DataStream fs) {
		super(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.items.add(new cfg.cmd.action.Item(fs));
		}
	}
}