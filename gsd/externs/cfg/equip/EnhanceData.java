package cfg.equip;
public final class EnhanceData extends cfg.CfgObject {
	public final static int TYPEID = 724201360;
	final public int getTypeId() { return TYPEID; }
	public final int addpropertyid;
	public final java.util.List<Integer> addvalues = new java.util.ArrayList<>();
	public EnhanceData(cfg.DataStream fs) {
		this.addpropertyid = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.addvalues.add(fs.getInt());
		}
	}
}