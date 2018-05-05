package cfg.equip;
public final class AnnealBonus extends cfg.CfgObject {
	public final static int TYPEID = 1317540776;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String name;
	public final java.util.List<cfg.equip.BonusData> bonus = new java.util.ArrayList<>();
	public AnnealBonus(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.name = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.bonus.add(new cfg.equip.BonusData(fs));
		}
	}
}