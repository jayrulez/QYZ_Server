package cfg.bonus;
public final class BaoZi extends cfg.CfgObject {
	public final static int TYPEID = 1196215846;
	final public int getTypeId() { return TYPEID; }
	public final int mealtype;
	public final int starthour;
	public final int startminute;
	public final int endhour;
	public final int endminute;
	public final cfg.cmd.action.TiLi gettili;
	public final cfg.cmd.condition.YuanBao requireyuanbao;
	public BaoZi(cfg.DataStream fs) {
		this.mealtype = fs.getInt();
		this.starthour = fs.getInt();
		this.startminute = fs.getInt();
		this.endhour = fs.getInt();
		this.endminute = fs.getInt();
		this.gettili = new cfg.cmd.action.TiLi(fs);
		this.requireyuanbao = new cfg.cmd.condition.YuanBao(fs);
	}
}