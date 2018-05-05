package cfg.ectype;
public final class ExpMonsterInfo extends cfg.CfgObject {
	public final static int TYPEID = 1826332471;
	final public int getTypeId() { return TYPEID; }
	public final String refmsg;
	public final java.util.List<cfg.ectype.CurrencyMonsterRef> monsterref = new java.util.ArrayList<>();
	public ExpMonsterInfo(cfg.DataStream fs) {
		this.refmsg = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.monsterref.add(new cfg.ectype.CurrencyMonsterRef(fs));
		}
	}
}