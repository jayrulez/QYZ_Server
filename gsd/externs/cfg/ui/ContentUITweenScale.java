package cfg.ui;
public final class ContentUITweenScale extends cfg.ui.ContentUITween {
	public final static int TYPEID = -1792326500;
	final public int getTypeId() { return TYPEID; }
	public final cfg.ui.Vector3 from;
	public final cfg.ui.Vector3 to;
	public ContentUITweenScale(cfg.DataStream fs) {
		super(fs);
		this.from = new cfg.ui.Vector3(fs);
		this.to = new cfg.ui.Vector3(fs);
	}
}