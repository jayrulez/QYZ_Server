package cfg.equip;
public final class JewelryLvlLimit extends cfg.CfgObject {
	public final static int TYPEID = -698026821;
	final public int getTypeId() { return TYPEID; }
	public final int level;
	public final java.util.List<Integer> jewelrylvl = new java.util.ArrayList<>();
	public JewelryLvlLimit(cfg.DataStream fs) {
		this.level = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.jewelrylvl.add(fs.getInt());
		}
	}
}