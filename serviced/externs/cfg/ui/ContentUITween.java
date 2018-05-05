package cfg.ui;
public abstract class ContentUITween extends cfg.ui.ContentUIBase {
	public final String style;
	public final cfg.ui.AnimationCurve animationCurve;
	public final float duration;
	public final float delay;
	public final int tweenGroup;
	public final boolean ignoreTimeScale;
	public ContentUITween(cfg.DataStream fs) {
		super(fs);
		this.style = fs.getString();
		this.animationCurve = new cfg.ui.AnimationCurve(fs);
		this.duration = fs.getFloat();
		this.delay = fs.getFloat();
		this.tweenGroup = fs.getInt();
		this.ignoreTimeScale = fs.getBool();
	}
}