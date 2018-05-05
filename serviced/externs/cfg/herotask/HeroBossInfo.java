package cfg.herotask;
public final class HeroBossInfo extends cfg.CfgObject {
	public final static int TYPEID = 43721466;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final cfg.map.Vector2 position;
	public final int monsterid;
	public HeroBossInfo(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.position = new cfg.map.Vector2(fs);
		this.monsterid = fs.getInt();
	}
}