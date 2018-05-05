package cfg.equip;
public final class BonusData extends cfg.CfgObject {
	public final static int TYPEID = -27733375;
	final public int getTypeId() { return TYPEID; }
	public final int bonuslevel;
	public final java.util.List<cfg.equip.EquipPropertyData> bonusvalue = new java.util.ArrayList<>();
	public BonusData(cfg.DataStream fs) {
		this.bonuslevel = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.bonusvalue.add(new cfg.equip.EquipPropertyData(fs));
		}
	}
}