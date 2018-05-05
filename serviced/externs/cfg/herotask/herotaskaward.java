package cfg.herotask;
public final class herotaskaward extends cfg.CfgObject {
	public final static int TYPEID = 1541847769;
	final public int getTypeId() { return TYPEID; }
	public final int level;
	public final int exp;
	public final int gold;
	public final cfg.cmd.action.Bonus extrabonus;
	public herotaskaward(cfg.DataStream fs) {
		this.level = fs.getInt();
		this.exp = fs.getInt();
		this.gold = fs.getInt();
		this.extrabonus = (cfg.cmd.action.Bonus)fs.getObject(fs.getString());
	}
}