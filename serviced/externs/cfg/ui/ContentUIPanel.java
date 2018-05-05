package cfg.ui;
public final class ContentUIPanel extends cfg.ui.ContentUIBase {
	public final static int TYPEID = 1260239079;
	final public int getTypeId() { return TYPEID; }
	public final float alpha;
	public final int depth;
	public final String clipping;
	public final cfg.ui.Vector2 clipOffset;
	public final cfg.ui.Vector4 baseClipRegion;
	public final cfg.ui.Vector2 clipSoftness;
	public final String clipTexture;
	public final String renderQueue;
	public final int sortingOrder;
	public final boolean generateNormals;
	public final boolean cullWhileDragging;
	public final boolean alwaysOnScreen;
	public final boolean softBorderPadding;
	public final boolean anchorOffset;
	public final boolean widgetsAreStatic;
	public final boolean showInPanelTool;
	public ContentUIPanel(cfg.DataStream fs) {
		super(fs);
		this.alpha = fs.getFloat();
		this.depth = fs.getInt();
		this.clipping = fs.getString();
		this.clipOffset = new cfg.ui.Vector2(fs);
		this.baseClipRegion = new cfg.ui.Vector4(fs);
		this.clipSoftness = new cfg.ui.Vector2(fs);
		this.clipTexture = fs.getString();
		this.renderQueue = fs.getString();
		this.sortingOrder = fs.getInt();
		this.generateNormals = fs.getBool();
		this.cullWhileDragging = fs.getBool();
		this.alwaysOnScreen = fs.getBool();
		this.softBorderPadding = fs.getBool();
		this.anchorOffset = fs.getBool();
		this.widgetsAreStatic = fs.getBool();
		this.showInPanelTool = fs.getBool();
	}
}