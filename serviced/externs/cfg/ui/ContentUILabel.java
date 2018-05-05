package cfg.ui;
public final class ContentUILabel extends cfg.ui.ContentUIWidgetBase {
	public final static int TYPEID = 1256533463;
	final public int getTypeId() { return TYPEID; }
	public final String fontType;
	public final String font;
	public final int fontSize;
	public final String fontStyle;
	public final String material;
	public final String text;
	public final String overflowMethod;
	public final int mMaxWidth;
	public final String alignment;
	public final String keepCrispWhenShrunk;
	public final boolean applyGradient;
	public final cfg.ui.Color gradientTop;
	public final cfg.ui.Color gradientBottom;
	public final String effectStyle;
	public final cfg.ui.Color effectColor;
	public final cfg.ui.Vector2 effectDistance;
	public final boolean useFloatSpacing;
	public final int spacingX;
	public final int spacingY;
	public final float floatSpacingX;
	public final float floatSpacingY;
	public final int maxLineCount;
	public final boolean supportEncoding;
	public final String symbolStyle;
	public ContentUILabel(cfg.DataStream fs) {
		super(fs);
		this.fontType = fs.getString();
		this.font = fs.getString();
		this.fontSize = fs.getInt();
		this.fontStyle = fs.getString();
		this.material = fs.getString();
		this.text = fs.getString();
		this.overflowMethod = fs.getString();
		this.mMaxWidth = fs.getInt();
		this.alignment = fs.getString();
		this.keepCrispWhenShrunk = fs.getString();
		this.applyGradient = fs.getBool();
		this.gradientTop = new cfg.ui.Color(fs);
		this.gradientBottom = new cfg.ui.Color(fs);
		this.effectStyle = fs.getString();
		this.effectColor = new cfg.ui.Color(fs);
		this.effectDistance = new cfg.ui.Vector2(fs);
		this.useFloatSpacing = fs.getBool();
		this.spacingX = fs.getInt();
		this.spacingY = fs.getInt();
		this.floatSpacingX = fs.getFloat();
		this.floatSpacingY = fs.getFloat();
		this.maxLineCount = fs.getInt();
		this.supportEncoding = fs.getBool();
		this.symbolStyle = fs.getString();
	}
}