package cfg.role;
public final class Revive extends cfg.CfgObject {
	public final static int TYPEID = -330073245;
	final public int getTypeId() { return TYPEID; }
	public static final int AUTOREVIVETIME = 30;
	public final cfg.cmd.condition.VipLimitsLite viprevivetimes;
	public final cfg.cmd.condition.YuanBao reviveYuanBao;
	public final cfg.cmd.condition.OneItem reviveitem;
	public final java.util.List<Integer> revivecost = new java.util.ArrayList<>();
	public Revive(cfg.DataStream fs) {
		this.viprevivetimes = new cfg.cmd.condition.VipLimitsLite(fs);
		this.reviveYuanBao = new cfg.cmd.condition.YuanBao(fs);
		this.reviveitem = new cfg.cmd.condition.OneItem(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.revivecost.add(fs.getInt());
		}
	}
}