package cfg.active;
public final class Findback extends cfg.CfgObject {
	public final static int TYPEID = -104142178;
	final public int getTypeId() { return TYPEID; }
	public final int lv;
	public final java.util.List<cfg.active.SystemLookBack> findsystemlist = new java.util.ArrayList<>();
	public Findback(cfg.DataStream fs) {
		this.lv = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.findsystemlist.add(new cfg.active.SystemLookBack(fs));
		}
	}
}