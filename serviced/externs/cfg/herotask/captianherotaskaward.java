package cfg.herotask;
public final class captianherotaskaward extends cfg.CfgObject {
	public final static int TYPEID = -1479172209;
	final public int getTypeId() { return TYPEID; }
	public final int level;
	public final cfg.cmd.action.Bonus extrabonus;
	public captianherotaskaward(cfg.DataStream fs) {
		this.level = fs.getInt();
		this.extrabonus = (cfg.cmd.action.Bonus)fs.getObject(fs.getString());
	}
}