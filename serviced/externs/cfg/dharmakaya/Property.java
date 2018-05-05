package cfg.dharmakaya;
public final class Property extends cfg.CfgObject {
	public final static int TYPEID = -6064878;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<cfg.equip.EquipPropertyData> gainability = new java.util.ArrayList<>();
	public Property(cfg.DataStream fs) {
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.gainability.add(new cfg.equip.EquipPropertyData(fs));
		}
	}
}