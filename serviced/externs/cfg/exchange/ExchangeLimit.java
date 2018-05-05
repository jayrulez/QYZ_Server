package cfg.exchange;
public final class ExchangeLimit extends cfg.CfgObject {
	public final static int TYPEID = -1486945417;
	final public int getTypeId() { return TYPEID; }
	public final cfg.cmd.condition.LevelLimit buylimit;
	public final cfg.cmd.condition.LevelLimit selllimit;
	public ExchangeLimit(cfg.DataStream fs) {
		this.buylimit = new cfg.cmd.condition.LevelLimit(fs);
		this.selllimit = new cfg.cmd.condition.LevelLimit(fs);
	}
}