package cfg.ui;
public final class Transform extends cfg.CfgObject {
	public final static int TYPEID = -167956036;
	final public int getTypeId() { return TYPEID; }
	public final cfg.ui.Vector3 position;
	public final cfg.ui.Vector3 eularangles;
	public final cfg.ui.Vector3 scale;
	public Transform(cfg.DataStream fs) {
		this.position = new cfg.ui.Vector3(fs);
		this.eularangles = new cfg.ui.Vector3(fs);
		this.scale = new cfg.ui.Vector3(fs);
	}
}