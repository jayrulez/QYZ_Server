package cfg.ectype;
public final class MultiPlayerEctype extends cfg.CfgObject {
	public final static int TYPEID = -1683056250;
	final public int getTypeId() { return TYPEID; }
	public static final int COINPOINT_OPEN_LEVEL = 30;
	public final cfg.cmd.condition.MinMaxLevel requirelevel;
	public final int fightforce;
	public final java.util.List<Integer> opentime = new java.util.ArrayList<>();
	public final int signuptime;
	public final int dailytimes;
	public final cfg.cmd.condition.VipLimits viptimes;
	public final java.util.List<cfg.cmd.action.OneItem> showbonusid = new java.util.ArrayList<>();
	public final String openbroadcast;
	public final int entertime;
	public final int waittime;
	public final java.util.List<cfg.ectype.GradeBonus> gradebonus = new java.util.ArrayList<>();
	public MultiPlayerEctype(cfg.DataStream fs) {
		this.requirelevel = new cfg.cmd.condition.MinMaxLevel(fs);
		this.fightforce = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.opentime.add(fs.getInt());
		}
		this.signuptime = fs.getInt();
		this.dailytimes = fs.getInt();
		this.viptimes = new cfg.cmd.condition.VipLimits(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.showbonusid.add(new cfg.cmd.action.OneItem(fs));
		}
		this.openbroadcast = fs.getString();
		this.entertime = fs.getInt();
		this.waittime = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.gradebonus.add(new cfg.ectype.GradeBonus(fs));
		}
	}
}