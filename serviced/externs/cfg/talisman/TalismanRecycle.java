package cfg.talisman;
public final class TalismanRecycle extends cfg.CfgObject {
	public final static int TYPEID = 759272335;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<cfg.talisman.RecycleExp> expitemid = new java.util.ArrayList<>();
	public final java.util.List<cfg.talisman.RecycleExp> qualitytalismanid = new java.util.ArrayList<>();
	public final java.util.List<Integer> allexpitemid = new java.util.ArrayList<>();
	public TalismanRecycle(cfg.DataStream fs) {
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.expitemid.add(new cfg.talisman.RecycleExp(fs));
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.qualitytalismanid.add(new cfg.talisman.RecycleExp(fs));
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.allexpitemid.add(fs.getInt());
		}
	}
}