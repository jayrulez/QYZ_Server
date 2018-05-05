package cfg.equip;
public final class Hat extends cfg.equip.MainEquip {
	public final static int TYPEID = 1521777331;
	final public int getTypeId() { return TYPEID; }
	public Hat(cfg.DataStream fs) {
		super(fs);
	}
}