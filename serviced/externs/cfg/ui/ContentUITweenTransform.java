package cfg.ui;
public final class ContentUITweenTransform extends cfg.ui.ContentUITween {
	public final static int TYPEID = 2102701502;
	final public int getTypeId() { return TYPEID; }
	public final cfg.ui.ContentUITransform from;
	public final cfg.ui.ContentUITransform to;
	public final boolean parentWhenFinished;
	public ContentUITweenTransform(cfg.DataStream fs) {
		super(fs);
		this.from = new cfg.ui.ContentUITransform(fs);
		this.to = new cfg.ui.ContentUITransform(fs);
		this.parentWhenFinished = fs.getBool();
	}
}