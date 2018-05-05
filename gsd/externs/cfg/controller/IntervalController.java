package cfg.controller;
public final class IntervalController extends cfg.controller.GlobalController {
	public final static int TYPEID = -775950871;
	final public int getTypeId() { return TYPEID; }
	public final int hour;
	public final int minute;
	public final int year;
	public final int month;
	public final int day;
	public final int intervaldays;
	public IntervalController(cfg.DataStream fs) {
		super(fs);
		this.hour = fs.getInt();
		this.minute = fs.getInt();
		this.year = fs.getInt();
		this.month = fs.getInt();
		this.day = fs.getInt();
		this.intervaldays = fs.getInt();
	}
}