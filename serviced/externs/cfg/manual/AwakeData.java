package cfg.manual;
public final class AwakeData extends cfg.CfgObject {
	public final static int TYPEID = -1750456623;
	final public int getTypeId() { return TYPEID; }
	public final int awakelevel;
	public final String desc;
	public final int propertytype;
	public final java.util.List<cfg.equip.EquipPropertyData> property = new java.util.ArrayList<>();
	public final int costitems;
	public AwakeData(cfg.DataStream fs) {
		this.awakelevel = fs.getInt();
		this.desc = fs.getString();
		this.propertytype = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.property.add(new cfg.equip.EquipPropertyData(fs));
		}
		this.costitems = fs.getInt();
	}
}