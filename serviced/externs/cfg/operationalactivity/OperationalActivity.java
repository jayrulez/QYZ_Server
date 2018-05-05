package cfg.operationalactivity;
public final class OperationalActivity extends cfg.CfgObject {
	public final static int TYPEID = -1304800054;
	final public int getTypeId() { return TYPEID; }
	public final int type;
	public final int displayorder;
	public final String title;
	public final String content;
	public final boolean showreddot;
	public final String texture;
	public final String unfinishlabel;
	public final String finishedlabel;
	public final String currencyname;
	public final int currencytype;
	public final cfg.common.DateTimeRange timerange;
	public final cfg.common.DateTimeRange receivetimerange;
	public final int intervalday;
	public final int timetype;
	public final int relativestarttime;
	public final int relativeendtime;
	public final boolean isstartactivity;
	public final java.util.List<cfg.operationalactivity.ActivityEntry> activityinfo = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.operationalactivity.ActivityEntry> activityinfo_id= new java.util.HashMap<>();
	public OperationalActivity(cfg.DataStream fs) {
		this.type = fs.getInt();
		this.displayorder = fs.getInt();
		this.title = fs.getString();
		this.content = fs.getString();
		this.showreddot = fs.getBool();
		this.texture = fs.getString();
		this.unfinishlabel = fs.getString();
		this.finishedlabel = fs.getString();
		this.currencyname = fs.getString();
		this.currencytype = fs.getInt();
		this.timerange = new cfg.common.DateTimeRange(fs);
		this.receivetimerange = new cfg.common.DateTimeRange(fs);
		this.intervalday = fs.getInt();
		this.timetype = fs.getInt();
		this.relativestarttime = fs.getInt();
		this.relativeendtime = fs.getInt();
		this.isstartactivity = fs.getBool();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.operationalactivity.ActivityEntry _v = new cfg.operationalactivity.ActivityEntry(fs);
			this.activityinfo.add(_v);
			this.activityinfo_id.put(_v.id, _v);
		}
	}
}