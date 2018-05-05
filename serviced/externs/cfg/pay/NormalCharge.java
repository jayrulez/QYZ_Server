package cfg.pay;
public final class NormalCharge extends cfg.pay.Charge {
	public final static int TYPEID = 1792350827;
	final public int getTypeId() { return TYPEID; }
	public final cfg.cmd.action.YuanBao getyuanbao;
	public final cfg.cmd.action.BindYuanBao firstgetbindyuanbao;
	public final cfg.cmd.action.BindYuanBao getbindyuanbao;
	public final String firstdescribetext;
	public final String describetext;
	public final String notetext;
	public final String backgourndimage;
	public NormalCharge(cfg.DataStream fs) {
		super(fs);
		this.getyuanbao = new cfg.cmd.action.YuanBao(fs);
		this.firstgetbindyuanbao = new cfg.cmd.action.BindYuanBao(fs);
		this.getbindyuanbao = new cfg.cmd.action.BindYuanBao(fs);
		this.firstdescribetext = fs.getString();
		this.describetext = fs.getString();
		this.notetext = fs.getString();
		this.backgourndimage = fs.getString();
	}
}