package cfg.pay;
public final class ActiveCharge extends cfg.pay.Charge {
	public final static int TYPEID = -1228568246;
	final public int getTypeId() { return TYPEID; }
	public final cfg.cmd.action.YuanBao getyuanbao;
	public final cfg.cmd.action.BindYuanBao firstgetbindyuanbao;
	public final cfg.cmd.action.BindYuanBao getbindyuanbao;
	public final String firstdescribetext;
	public final String describetext;
	public final String notetext;
	public final String backgourndimage;
	public final cfg.cmd.condition.DayLimit daylimit;
	public final cfg.cmd.action.MultiBonus extrabonus;
	public final cfg.cmd.action.MultiBonus clientshowitem;
	public ActiveCharge(cfg.DataStream fs) {
		super(fs);
		this.getyuanbao = new cfg.cmd.action.YuanBao(fs);
		this.firstgetbindyuanbao = new cfg.cmd.action.BindYuanBao(fs);
		this.getbindyuanbao = new cfg.cmd.action.BindYuanBao(fs);
		this.firstdescribetext = fs.getString();
		this.describetext = fs.getString();
		this.notetext = fs.getString();
		this.backgourndimage = fs.getString();
		this.daylimit = new cfg.cmd.condition.DayLimit(fs);
		this.extrabonus = new cfg.cmd.action.MultiBonus(fs);
		this.clientshowitem = new cfg.cmd.action.MultiBonus(fs);
	}
}