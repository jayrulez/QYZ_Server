package cfg.pay;
public final class MonthCard extends cfg.pay.Charge {
	public final static int TYPEID = -2117107776;
	final public int getTypeId() { return TYPEID; }
	public final cfg.cmd.action.YuanBao getyuanbao;
	public final cfg.cmd.action.BindYuanBao getbindyuanbao;
	public final int days;
	public final String describetext1;
	public final String describetext2;
	public final String notetext;
	public final String backgourndimage;
	public MonthCard(cfg.DataStream fs) {
		super(fs);
		this.getyuanbao = new cfg.cmd.action.YuanBao(fs);
		this.getbindyuanbao = new cfg.cmd.action.BindYuanBao(fs);
		this.days = fs.getInt();
		this.describetext1 = fs.getString();
		this.describetext2 = fs.getString();
		this.notetext = fs.getString();
		this.backgourndimage = fs.getString();
	}
}