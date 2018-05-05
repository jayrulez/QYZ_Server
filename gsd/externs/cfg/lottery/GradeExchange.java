package cfg.lottery;
public final class GradeExchange extends cfg.CfgObject {
	public final static int TYPEID = 1225924363;
	final public int getTypeId() { return TYPEID; }
	public final int cost;
	public final int currencytype;
	public final java.util.List<cfg.lottery.Exchange> exchangelist = new java.util.ArrayList<>();
	public GradeExchange(cfg.DataStream fs) {
		this.cost = fs.getInt();
		this.currencytype = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.exchangelist.add(new cfg.lottery.Exchange(fs));
		}
	}
}