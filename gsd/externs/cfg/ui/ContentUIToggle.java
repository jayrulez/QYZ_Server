package cfg.ui;
public final class ContentUIToggle extends cfg.ui.ContentUIBase {
	public final static int TYPEID = 539945169;
	final public int getTypeId() { return TYPEID; }
	public final int group;
	public final boolean optionCanBeNone;
	public final boolean value;
	public final cfg.ui.ContentUITransform activeSprite;
	public final String activeAnimation;
	public final boolean instantTween;
	public ContentUIToggle(cfg.DataStream fs) {
		super(fs);
		this.group = fs.getInt();
		this.optionCanBeNone = fs.getBool();
		this.value = fs.getBool();
		this.activeSprite = new cfg.ui.ContentUITransform(fs);
		this.activeAnimation = fs.getString();
		this.instantTween = fs.getBool();
	}
}