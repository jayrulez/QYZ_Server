package cfg.redpacket;
public final class Redpacket extends cfg.CfgObject {
	public final static int TYPEID = -1264931750;
	final public int getTypeId() { return TYPEID; }
	public static final int MAX_SHOW_NUM = 30;
	public final cfg.common.DateTimeRange datetime;
	public final String closepic;
	public final String openpic;
	public final String sucpic;
	public final String faildecs;
	public final String updecs;
	public final cfg.cmd.condition.DayLimit givelimit;
	public final cfg.cmd.condition.DayLimit recievelimit;
	public final java.util.List<cfg.redpacket.RedpacketInfo> redpacketinfo = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.redpacket.RedpacketInfo> redpacketinfo_id= new java.util.HashMap<>();
	public Redpacket(cfg.DataStream fs) {
		this.datetime = new cfg.common.DateTimeRange(fs);
		this.closepic = fs.getString();
		this.openpic = fs.getString();
		this.sucpic = fs.getString();
		this.faildecs = fs.getString();
		this.updecs = fs.getString();
		this.givelimit = new cfg.cmd.condition.DayLimit(fs);
		this.recievelimit = new cfg.cmd.condition.DayLimit(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.redpacket.RedpacketInfo _v = new cfg.redpacket.RedpacketInfo(fs);
			this.redpacketinfo.add(_v);
			this.redpacketinfo_id.put(_v.id, _v);
		}
	}
}