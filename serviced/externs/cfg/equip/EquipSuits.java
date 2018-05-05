package cfg.equip;
public final class EquipSuits extends cfg.CfgObject {
	public final static int TYPEID = 708962718;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String name;
	public final java.util.List<Integer> includeid = new java.util.ArrayList<>();
	public final java.util.List<cfg.equip.SuitsData> suitsbonus = new java.util.ArrayList<>();
	public EquipSuits(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.name = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.includeid.add(fs.getInt());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.suitsbonus.add(new cfg.equip.SuitsData(fs));
		}
	}
}