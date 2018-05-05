package cfg.ui;
public final class ContentUITable extends cfg.ui.ContentUIBase {
	public final static int TYPEID = 1263921841;
	final public int getTypeId() { return TYPEID; }
	public final int columns;
	public final String direction;
	public final String sorting;
	public final String pivot;
	public final String cellAlignment;
	public final boolean hideInactive;
	public final boolean keepWithinPanel;
	public final cfg.ui.Vector2 padding;
	public ContentUITable(cfg.DataStream fs) {
		super(fs);
		this.columns = fs.getInt();
		this.direction = fs.getString();
		this.sorting = fs.getString();
		this.pivot = fs.getString();
		this.cellAlignment = fs.getString();
		this.hideInactive = fs.getBool();
		this.keepWithinPanel = fs.getBool();
		this.padding = new cfg.ui.Vector2(fs);
	}
}