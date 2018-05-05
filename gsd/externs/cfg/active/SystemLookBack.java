package cfg.active;
public final class SystemLookBack extends cfg.CfgObject {
	public final static int TYPEID = -106975885;
	final public int getTypeId() { return TYPEID; }
	public final int eventtype;
	public final String name;
	public final String systemicon;
	public final cfg.cmd.condition.XuNiBi xunibicost;
	public final cfg.cmd.action.MultiBonus xunibiaward;
	public final cfg.cmd.condition.YuanBao yuanbaocost;
	public final cfg.cmd.action.MultiBonus yuanbaoaward;
	public SystemLookBack(cfg.DataStream fs) {
		this.eventtype = fs.getInt();
		this.name = fs.getString();
		this.systemicon = fs.getString();
		this.xunibicost = new cfg.cmd.condition.XuNiBi(fs);
		this.xunibiaward = new cfg.cmd.action.MultiBonus(fs);
		this.yuanbaocost = new cfg.cmd.condition.YuanBao(fs);
		this.yuanbaoaward = new cfg.cmd.action.MultiBonus(fs);
	}
}