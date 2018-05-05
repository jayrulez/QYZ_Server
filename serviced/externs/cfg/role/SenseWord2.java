package cfg.role;
public final class SenseWord2 extends cfg.CfgObject {
	public final static int TYPEID = -29626776;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<String> words = new java.util.ArrayList<>();
	public SenseWord2(cfg.DataStream fs) {
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.words.add(fs.getString());
		}
	}
}