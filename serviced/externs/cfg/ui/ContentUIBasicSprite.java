package cfg.ui;
public abstract class ContentUIBasicSprite extends cfg.ui.ContentUIWidgetBase {
	public final String type;
	public final String flip;
	public final String centerType;
	public final String leftType;
	public final String rightType;
	public final String bottomType;
	public final String topType;
	public final String fillDirection;
	public final float fillAmount;
	public final boolean invert;
	public ContentUIBasicSprite(cfg.DataStream fs) {
		super(fs);
		this.type = fs.getString();
		this.flip = fs.getString();
		this.centerType = fs.getString();
		this.leftType = fs.getString();
		this.rightType = fs.getString();
		this.bottomType = fs.getString();
		this.topType = fs.getString();
		this.fillDirection = fs.getString();
		this.fillAmount = fs.getFloat();
		this.invert = fs.getBool();
	}
}