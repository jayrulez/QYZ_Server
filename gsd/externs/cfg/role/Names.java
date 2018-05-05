package cfg.role;
public final class Names extends cfg.CfgObject {
	public final static int TYPEID = 1371003770;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<String> names = new java.util.ArrayList<>();
	public Names(cfg.DataStream fs) {
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.names.add(fs.getString());
		}
	}
}