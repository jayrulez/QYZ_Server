package cfg.cmd.condition;
public final class Currencys extends cfg.cmd.condition.Condition {
	public final static int TYPEID = 1192555281;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<cfg.cmd.condition.Currency> currencys = new java.util.ArrayList<>();
	public Currencys(cfg.DataStream fs) {
		super(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.currencys.add(new cfg.cmd.condition.Currency(fs));
		}
	}
}