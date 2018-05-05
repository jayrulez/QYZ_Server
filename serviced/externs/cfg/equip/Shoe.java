package cfg.equip;
public final class Shoe extends cfg.equip.MainEquip {
	public final static int TYPEID = -69208621;
	final public int getTypeId() { return TYPEID; }
	public Shoe(cfg.DataStream fs) {
		super(fs);
	}
}