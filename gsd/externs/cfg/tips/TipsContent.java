package cfg.tips;
public final class TipsContent extends cfg.CfgObject {
	public final static int TYPEID = -1261879147;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String content;
	public TipsContent(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.content = fs.getString();
	}
}