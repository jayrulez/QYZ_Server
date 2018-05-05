package cfg.equip;
public final class Cloth extends cfg.equip.MainEquip {
	public final static int TYPEID = 2134843442;
	final public int getTypeId() { return TYPEID; }
	public final String male;
	public final String female;
	public Cloth(cfg.DataStream fs) {
		super(fs);
		this.male = fs.getString();
		this.female = fs.getString();
	}
}