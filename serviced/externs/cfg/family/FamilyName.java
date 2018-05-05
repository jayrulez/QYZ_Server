package cfg.family;
public final class FamilyName extends cfg.CfgObject {
	public final static int TYPEID = 1736352239;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<String> firstname = new java.util.ArrayList<>();
	public final java.util.List<String> lastname = new java.util.ArrayList<>();
	public FamilyName(cfg.DataStream fs) {
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.firstname.add(fs.getString());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.lastname.add(fs.getString());
		}
	}
}