package cfg.equip;
public final class EquipAppend extends cfg.CfgObject {
	public final static int TYPEID = -16740478;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String name;
	public final java.util.List<cfg.equip.EnhanceData> adddata = new java.util.ArrayList<>();
	public EquipAppend(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.name = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.adddata.add(new cfg.equip.EnhanceData(fs));
		}
	}
}