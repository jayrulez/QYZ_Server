package cfg.guide;
public final class WidgetOfList extends cfg.guide.LockObject {
	public final static int TYPEID = 7126549;
	final public int getTypeId() { return TYPEID; }
	public final int index;
	public final String widgetname;
	public WidgetOfList(cfg.DataStream fs) {
		super(fs);
		this.index = fs.getInt();
		this.widgetname = fs.getString();
	}
}