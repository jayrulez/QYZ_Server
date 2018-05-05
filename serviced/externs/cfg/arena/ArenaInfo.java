package cfg.arena;
public final class ArenaInfo extends cfg.CfgObject {
	public final static int TYPEID = -1207684988;
	final public int getTypeId() { return TYPEID; }
	public final String index;
	public final String content;
	public ArenaInfo(cfg.DataStream fs) {
		this.index = fs.getString();
		this.content = fs.getString();
	}
}