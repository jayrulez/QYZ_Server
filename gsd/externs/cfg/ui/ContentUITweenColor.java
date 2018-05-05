package cfg.ui;
public final class ContentUITweenColor extends cfg.ui.ContentUITween {
	public final static int TYPEID = -1806734667;
	final public int getTypeId() { return TYPEID; }
	public final cfg.ui.Color from;
	public final cfg.ui.Color to;
	public ContentUITweenColor(cfg.DataStream fs) {
		super(fs);
		this.from = new cfg.ui.Color(fs);
		this.to = new cfg.ui.Color(fs);
	}
}