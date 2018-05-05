package cfg.role;
public final class Name extends cfg.CfgObject {
	public final static int TYPEID = 736962585;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<cfg.role.Names> firstnames = new java.util.ArrayList<>();
	public final cfg.role.Names lastnames;
	public final java.util.List<cfg.role.DecorateName> deconames = new java.util.ArrayList<>();
	public Name(cfg.DataStream fs) {
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.firstnames.add(new cfg.role.Names(fs));
		}
		this.lastnames = new cfg.role.Names(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.deconames.add(new cfg.role.DecorateName(fs));
		}
	}
}