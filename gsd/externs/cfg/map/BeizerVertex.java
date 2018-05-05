package cfg.map;
public final class BeizerVertex extends cfg.CfgObject {
	public final static int TYPEID = -1775698655;
	final public int getTypeId() { return TYPEID; }
	public final cfg.map.Vector3 position;
	public final cfg.map.Vector3 leftctrl;
	public final cfg.map.Vector3 rightctrl;
	public BeizerVertex(cfg.DataStream fs) {
		this.position = new cfg.map.Vector3(fs);
		this.leftctrl = new cfg.map.Vector3(fs);
		this.rightctrl = new cfg.map.Vector3(fs);
	}
}