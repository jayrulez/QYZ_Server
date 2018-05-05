package cfg.ui;
public final class ContentUIGrid extends cfg.ui.ContentUIBase {
	public final static int TYPEID = -1067977725;
	final public int getTypeId() { return TYPEID; }
	public final String arrangement;
	public final String style;
	public final float cellWidth;
	public final float cellHeight;
	public final int maxPerLine;
	public final boolean animateSmoothly;
	public final boolean hideInactive;
	public final boolean keepWithinPanel;
	public ContentUIGrid(cfg.DataStream fs) {
		super(fs);
		this.arrangement = fs.getString();
		this.style = fs.getString();
		this.cellWidth = fs.getFloat();
		this.cellHeight = fs.getFloat();
		this.maxPerLine = fs.getInt();
		this.animateSmoothly = fs.getBool();
		this.hideInactive = fs.getBool();
		this.keepWithinPanel = fs.getBool();
	}
}