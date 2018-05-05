package cfg.ui;
public final class ContentUIScrollView extends cfg.ui.ContentUIBase {
	public final static int TYPEID = 1625787919;
	final public int getTypeId() { return TYPEID; }
	public final String contentPivot;
	public final String movement;
	public final String dragEffect;
	public final float scrollWheelFactor;
	public final float momentumAmount;
	public final boolean restrictWithinPanel;
	public final boolean disableDragIfFits;
	public final boolean smoothDragStart;
	public final boolean iOSDragEmulation;
	public ContentUIScrollView(cfg.DataStream fs) {
		super(fs);
		this.contentPivot = fs.getString();
		this.movement = fs.getString();
		this.dragEffect = fs.getString();
		this.scrollWheelFactor = fs.getFloat();
		this.momentumAmount = fs.getFloat();
		this.restrictWithinPanel = fs.getBool();
		this.disableDragIfFits = fs.getBool();
		this.smoothDragStart = fs.getBool();
		this.iOSDragEmulation = fs.getBool();
	}
}