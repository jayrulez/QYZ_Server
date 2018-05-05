package cfg.role;
public final class PinYinSenseWords extends cfg.CfgObject {
	public final static int TYPEID = -1502873966;
	final public int getTypeId() { return TYPEID; }
	public final String pinyin;
	public final int groupid;
	public final int weight;
	public PinYinSenseWords(cfg.DataStream fs) {
		this.pinyin = fs.getString();
		this.groupid = fs.getInt();
		this.weight = fs.getInt();
	}
}