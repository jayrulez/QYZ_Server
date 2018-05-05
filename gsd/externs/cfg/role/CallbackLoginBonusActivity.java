package cfg.role;
public final class CallbackLoginBonusActivity extends cfg.CfgObject {
	public final static int TYPEID = 1063739288;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final int notloginday;
	public final cfg.cmd.condition.MinLevel levellimit;
	public final cfg.cmd.condition.MinVipLevel viplevellimit;
	public final cfg.common.DateTimeRange openrange;
	public final String mailtitle;
	public final String mailcontent;
	public final cfg.cmd.action.MultiBonus firstlogincommonbonus;
	public final java.util.List<cfg.role.IndexBonus> firstloginbonusbylevel = new java.util.ArrayList<>();
	public final java.util.List<cfg.role.IndexBonus> accumulatelogindaybonus = new java.util.ArrayList<>();
	public final java.util.Map<Integer, cfg.role.IndexBonus> accumulatelogindaybonus_index= new java.util.HashMap<>();
	public CallbackLoginBonusActivity(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.notloginday = fs.getInt();
		this.levellimit = new cfg.cmd.condition.MinLevel(fs);
		this.viplevellimit = new cfg.cmd.condition.MinVipLevel(fs);
		this.openrange = new cfg.common.DateTimeRange(fs);
		this.mailtitle = fs.getString();
		this.mailcontent = fs.getString();
		this.firstlogincommonbonus = new cfg.cmd.action.MultiBonus(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.firstloginbonusbylevel.add(new cfg.role.IndexBonus(fs));
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final cfg.role.IndexBonus _v = new cfg.role.IndexBonus(fs);
			this.accumulatelogindaybonus.add(_v);
			this.accumulatelogindaybonus_index.put(_v.index, _v);
		}
	}
}