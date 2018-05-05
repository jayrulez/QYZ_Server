package cfg.equip;
public final class JadeEnhanceData extends cfg.CfgObject {
	public final static int TYPEID = 317094008;
	final public int getTypeId() { return TYPEID; }
	public final int enhancetypeid;
	public final String enhancetypename;
	public final cfg.cmd.condition.Currency currency;
	public final int minbonus;
	public final int maxbonus;
	public final float criticalrate;
	public final int criticalvalue;
	public JadeEnhanceData(cfg.DataStream fs) {
		this.enhancetypeid = fs.getInt();
		this.enhancetypename = fs.getString();
		this.currency = new cfg.cmd.condition.Currency(fs);
		this.minbonus = fs.getInt();
		this.maxbonus = fs.getInt();
		this.criticalrate = fs.getFloat();
		this.criticalvalue = fs.getInt();
	}
}