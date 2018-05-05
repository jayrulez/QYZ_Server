package cfg.ectype;
public final class GodMonsterInfo extends cfg.CfgObject {
	public final static int TYPEID = 1906849656;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final cfg.map.Vector2 position;
	public final float orientangle;
	public GodMonsterInfo(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.position = new cfg.map.Vector2(fs);
		this.orientangle = fs.getFloat();
	}
}