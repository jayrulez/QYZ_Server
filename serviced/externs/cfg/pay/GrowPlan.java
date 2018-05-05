package cfg.pay;
public final class GrowPlan extends cfg.pay.Charge {
	public final static int TYPEID = -904554036;
	final public int getTypeId() { return TYPEID; }
	public final int growplantype;
	public final int startdayindex;
	public final int totalday;
	public final String notetext;
	public final int thresholddaynum;
	public GrowPlan(cfg.DataStream fs) {
		super(fs);
		this.growplantype = fs.getInt();
		this.startdayindex = fs.getInt();
		this.totalday = fs.getInt();
		this.notetext = fs.getString();
		this.thresholddaynum = fs.getInt();
	}
}