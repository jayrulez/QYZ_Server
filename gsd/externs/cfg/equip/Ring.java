package cfg.equip;
public final class Ring extends cfg.equip.Accessory {
	public final static int TYPEID = -69237480;
	final public int getTypeId() { return TYPEID; }
	public Ring(cfg.DataStream fs) {
		super(fs);
	}
}