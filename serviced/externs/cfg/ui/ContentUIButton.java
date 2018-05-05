package cfg.ui;
public final class ContentUIButton extends cfg.ui.ContentUIBase {
	public final static int TYPEID = 30561455;
	final public int getTypeId() { return TYPEID; }
	public final cfg.ui.ContentUITransform tweenTarget;
	public final boolean dragHighlight;
	public final float duration;
	public final cfg.ui.Color hover;
	public final cfg.ui.Color pressed;
	public final cfg.ui.Color disabledColor;
	public final String hoverSprite2D;
	public final String pressedSprite2D;
	public final String disabledSprite2D;
	public ContentUIButton(cfg.DataStream fs) {
		super(fs);
		this.tweenTarget = new cfg.ui.ContentUITransform(fs);
		this.dragHighlight = fs.getBool();
		this.duration = fs.getFloat();
		this.hover = new cfg.ui.Color(fs);
		this.pressed = new cfg.ui.Color(fs);
		this.disabledColor = new cfg.ui.Color(fs);
		this.hoverSprite2D = fs.getString();
		this.pressedSprite2D = fs.getString();
		this.disabledSprite2D = fs.getString();
	}
}