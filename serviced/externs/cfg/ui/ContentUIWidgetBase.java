package cfg.ui;
public abstract class ContentUIWidgetBase extends cfg.ui.ContentUIBase {
	public final int depth;
	public final int width;
	public final int height;
	public final String pivot;
	public final cfg.ui.Color color;
	public final boolean autoResizeBoxCollider;
	public ContentUIWidgetBase(cfg.DataStream fs) {
		super(fs);
		this.depth = fs.getInt();
		this.width = fs.getInt();
		this.height = fs.getInt();
		this.pivot = fs.getString();
		this.color = new cfg.ui.Color(fs);
		this.autoResizeBoxCollider = fs.getBool();
	}
}