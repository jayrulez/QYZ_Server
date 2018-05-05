package cfg.pet;
public final class Property extends cfg.pet.AwakeEffect {
	public final static int TYPEID = -20266194;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<cfg.equip.EquipPropertyData> gainability = new java.util.ArrayList<>();
	public Property(cfg.DataStream fs) {
		super(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.gainability.add(new cfg.equip.EquipPropertyData(fs));
		}
	}
}