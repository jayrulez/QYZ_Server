package cfg.role;
public final class KeySet extends cfg.CfgObject {
	public final static int TYPEID = -530409583;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<cfg.role.Key> keys = new java.util.ArrayList<>();
	public KeySet(cfg.DataStream fs) {
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.keys.add(new cfg.role.Key(fs));
		}
	}
}