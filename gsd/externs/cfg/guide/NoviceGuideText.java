package cfg.guide;
public final class NoviceGuideText extends cfg.CfgObject {
	public final static int TYPEID = 1413684791;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String desc;
	public NoviceGuideText(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.desc = fs.getString();
	}
}