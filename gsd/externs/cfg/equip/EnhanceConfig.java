package cfg.equip;
public final class EnhanceConfig extends cfg.CfgObject {
	public final static int TYPEID = 156934568;
	final public int getTypeId() { return TYPEID; }
	public final int level;
	public final int anneallimit;
	public final int appendlimit;
	public EnhanceConfig(cfg.DataStream fs) {
		this.level = fs.getInt();
		this.anneallimit = fs.getInt();
		this.appendlimit = fs.getInt();
	}
}