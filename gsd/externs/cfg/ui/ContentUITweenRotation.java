package cfg.ui;
public final class ContentUITweenRotation extends cfg.ui.ContentUITween {
	public final static int TYPEID = 686308076;
	final public int getTypeId() { return TYPEID; }
	public final cfg.ui.Vector3 from;
	public final cfg.ui.Vector3 to;
	public ContentUITweenRotation(cfg.DataStream fs) {
		super(fs);
		this.from = new cfg.ui.Vector3(fs);
		this.to = new cfg.ui.Vector3(fs);
	}
}