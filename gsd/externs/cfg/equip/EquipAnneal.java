package cfg.equip;
public final class EquipAnneal extends cfg.CfgObject {
	public final static int TYPEID = -18647497;
	final public int getTypeId() { return TYPEID; }
	public static final int[] FAIL_REDUCE_NUMBER = {0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
	public static final int FAIL_REDUCE_BASELEVEL = 5;
	public final int id;
	public final String name;
	public final java.util.List<cfg.equip.EnhanceData> adddata = new java.util.ArrayList<>();
	public EquipAnneal(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.name = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.adddata.add(new cfg.equip.EnhanceData(fs));
		}
	}
}