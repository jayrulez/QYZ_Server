package cfg.ui;
public final class ContentUITweenAlpha extends cfg.ui.ContentUITween {
	public final static int TYPEID = -1808667472;
	final public int getTypeId() { return TYPEID; }
	public final float from;
	public final float to;
	public ContentUITweenAlpha(cfg.DataStream fs) {
		super(fs);
		this.from = fs.getFloat();
		this.to = fs.getFloat();
	}
}