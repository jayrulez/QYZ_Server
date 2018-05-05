package cfg.ectype;
public final class slowtime extends cfg.CfgObject {
	public final static int TYPEID = 1124545306;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final float rate;
	public final float duration;
	public slowtime(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.rate = fs.getFloat();
		this.duration = fs.getFloat();
	}
}