package cfg.role;
public final class LegalWord extends cfg.CfgObject {
	public final static int TYPEID = -443337579;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<String> words = new java.util.ArrayList<>();
	public LegalWord(cfg.DataStream fs) {
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.words.add(fs.getString());
		}
	}
}