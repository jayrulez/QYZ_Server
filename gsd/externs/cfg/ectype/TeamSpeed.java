package cfg.ectype;
public final class TeamSpeed extends cfg.CfgObject {
	public final static int TYPEID = 1526192766;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final int battlelast;
	public final int singuplast;
	public final String desc;
	public final cfg.ectype.BroadcastInfo broadcastinfo;
	public final int teamernumber;
	public final cfg.cmd.condition.MinLevel openlevel;
	public final int matchinterval;
	public final int timeout;
	public final java.util.List<Integer> matchvipdeltagroup = new java.util.ArrayList<>();
	public final int matchsucccountdown;
	public final java.util.List<cfg.common.DayTimeRange> timeinfo = new java.util.ArrayList<>();
	public final cfg.cmd.condition.VipLimitsLite dailylimit;
	public final java.util.List<cfg.ectype.SpeedLvMsg> lvmsg = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.ectype.SpeedLvMsg> lvmsg_id= new java.util.HashMap<>();
	public TeamSpeed(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.battlelast = fs.getInt();
		this.singuplast = fs.getInt();
		this.desc = fs.getString();
		this.broadcastinfo = new cfg.ectype.BroadcastInfo(fs);
		this.teamernumber = fs.getInt();
		this.openlevel = new cfg.cmd.condition.MinLevel(fs);
		this.matchinterval = fs.getInt();
		this.timeout = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.matchvipdeltagroup.add(fs.getInt());
		}
		this.matchsucccountdown = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.timeinfo.add(new cfg.common.DayTimeRange(fs));
		}
		this.dailylimit = new cfg.cmd.condition.VipLimitsLite(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.ectype.SpeedLvMsg _v = new cfg.ectype.SpeedLvMsg(fs);
			this.lvmsg.add(_v);
			this.lvmsg_id.put(_v.id, _v);
		}
	}
}