package cfg.task;
public final class HintInfo extends cfg.CfgObject {
	public final static int TYPEID = 441966132;
	final public int getTypeId() { return TYPEID; }
	public final String acceptedhint;
	public final String unacceptedhint;
	public final String donehint;
	public HintInfo(cfg.DataStream fs) {
		this.acceptedhint = fs.getString();
		this.unacceptedhint = fs.getString();
		this.donehint = fs.getString();
	}
}