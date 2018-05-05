package cfg.role;
public final class SenseWord extends cfg.CfgObject {
	public final static int TYPEID = -2079165686;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<String> words = new java.util.ArrayList<>();
	public SenseWord(cfg.DataStream fs) {
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.words.add(fs.getString());
		}
	}
}