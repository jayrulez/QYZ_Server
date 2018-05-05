package cfg.equip;
public final class AccessoryGenerate extends cfg.CfgObject {
	public final static int TYPEID = 500266175;
	final public int getTypeId() { return TYPEID; }
	public final int groupid;
	public final String whatisthis;
	public final java.util.List<cfg.equip.AccessoryProperty> propertylist = new java.util.ArrayList<>();
	public AccessoryGenerate(cfg.DataStream fs) {
		this.groupid = fs.getInt();
		this.whatisthis = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.propertylist.add(new cfg.equip.AccessoryProperty(fs));
		}
	}
}