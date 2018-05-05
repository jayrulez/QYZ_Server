package cfg.talisman;
public final class Property extends cfg.talisman.AwakeEffect {
	public final static int TYPEID = 131489928;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<cfg.equip.EquipPropertyData> gainability = new java.util.ArrayList<>();
	public Property(cfg.DataStream fs) {
		super(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.gainability.add(new cfg.equip.EquipPropertyData(fs));
		}
	}
}