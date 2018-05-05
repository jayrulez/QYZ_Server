package cfg.cmd.condition;
public final class Items extends cfg.cmd.condition.Condition {
	public final static int TYPEID = 416114639;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<cfg.cmd.condition.Item> items = new java.util.ArrayList<>();
	public Items(cfg.DataStream fs) {
		super(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.items.add(new cfg.cmd.condition.Item(fs));
		}
	}
}