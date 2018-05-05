package cfg.bonus;
public final class VipBonus extends cfg.CfgObject {
	public final static int TYPEID = 135929307;
	final public int getTypeId() { return TYPEID; }
	public final int viplevel;
	public final int needcharge;
	public final java.util.List<String> bonustext = new java.util.ArrayList<>();
	public final cfg.cmd.action.Items gainbonus;
	public final cfg.cmd.condition.Limit buylimit;
	public final cfg.cmd.condition.YuanBao price;
	public final int showprice;
	public VipBonus(cfg.DataStream fs) {
		this.viplevel = fs.getInt();
		this.needcharge = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.bonustext.add(fs.getString());
		}
		this.gainbonus = new cfg.cmd.action.Items(fs);
		this.buylimit = new cfg.cmd.condition.Limit(fs);
		this.price = new cfg.cmd.condition.YuanBao(fs);
		this.showprice = fs.getInt();
	}
}