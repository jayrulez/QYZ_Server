package cfg.equip;
public final class AccessoryConfig extends cfg.CfgObject {
	public final static int TYPEID = 1236020492;
	final public int getTypeId() { return TYPEID; }
	public final cfg.cmd.condition.XuNiBi washcost;
	public final cfg.cmd.condition.YuanBao abandoncost;
	public AccessoryConfig(cfg.DataStream fs) {
		this.washcost = new cfg.cmd.condition.XuNiBi(fs);
		this.abandoncost = new cfg.cmd.condition.YuanBao(fs);
	}
}