package cfg.controller;
public final class MonthlyController extends cfg.controller.GlobalController {
	public final static int TYPEID = 1153798881;
	final public int getTypeId() { return TYPEID; }
	public final int day;
	public final int hour;
	public final int minute;
	public MonthlyController(cfg.DataStream fs) {
		super(fs);
		this.day = fs.getInt();
		this.hour = fs.getInt();
		this.minute = fs.getInt();
	}
}