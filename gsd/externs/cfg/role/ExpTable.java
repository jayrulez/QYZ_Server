package cfg.role;
public final class ExpTable extends cfg.CfgObject {
	public final static int TYPEID = -1063599553;
	final public int getTypeId() { return TYPEID; }
	public final int level;
	public final long exp;
	public final long bonusexp;
	public final int partyexp;
	public final int offlineexp;
	public ExpTable(cfg.DataStream fs) {
		this.level = fs.getInt();
		this.exp = fs.getLong();
		this.bonusexp = fs.getLong();
		this.partyexp = fs.getInt();
		this.offlineexp = fs.getInt();
	}
}