package cfg.pet;
public final class KarmaProp extends cfg.CfgObject {
	public final static int TYPEID = -1466971718;
	final public int getTypeId() { return TYPEID; }
	public final int level;
	public final java.util.List<cfg.equip.EquipPropertyData> karmadata = new java.util.ArrayList<>();
	public KarmaProp(cfg.DataStream fs) {
		this.level = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.karmadata.add(new cfg.equip.EquipPropertyData(fs));
		}
	}
}