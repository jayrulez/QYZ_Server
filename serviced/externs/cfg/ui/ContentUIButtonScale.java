package cfg.ui;
public final class ContentUIButtonScale extends cfg.ui.ContentUIBase {
	public final static int TYPEID = -673031717;
	final public int getTypeId() { return TYPEID; }
	public final cfg.ui.ContentUITransform tweenTarget;
	public final cfg.ui.Vector3 hover;
	public final cfg.ui.Vector3 pressed;
	public final float duration;
	public ContentUIButtonScale(cfg.DataStream fs) {
		super(fs);
		this.tweenTarget = new cfg.ui.ContentUITransform(fs);
		this.hover = new cfg.ui.Vector3(fs);
		this.pressed = new cfg.ui.Vector3(fs);
		this.duration = fs.getFloat();
	}
}