package cfg.equip;
public final class AmuletWash extends cfg.CfgObject {
	public final static int TYPEID = -1782902183;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<Integer> probablilty = new java.util.ArrayList<>();
	public AmuletWash(cfg.DataStream fs) {
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.probablilty.add(fs.getInt());
		}
	}
}