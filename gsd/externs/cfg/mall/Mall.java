package cfg.mall;
public abstract class Mall extends cfg.CfgObject {
	public final int id;
	public final int displayorder;
	public final cfg.cmd.action.OneItem itemid;
	public final cfg.cmd.action.BindType bindtype;
	public final cfg.cmd.condition.Currency cost;
	public final String introduce;
	public final cfg.cmd.condition.Limits limitlist;
	public Mall(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.displayorder = fs.getInt();
		this.itemid = new cfg.cmd.action.OneItem(fs);
		this.bindtype = new cfg.cmd.action.BindType(fs);
		this.cost = new cfg.cmd.condition.Currency(fs);
		this.introduce = fs.getString();
		this.limitlist = new cfg.cmd.condition.Limits(fs);
	}
}