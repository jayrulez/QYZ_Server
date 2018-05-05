package cfg.ui;
public final class EctypeGrade extends cfg.CfgObject {
	public final static int TYPEID = 921267247;
	final public int getTypeId() { return TYPEID; }
	public final String index;
	public final float time;
	public EctypeGrade(cfg.DataStream fs) {
		this.index = fs.getString();
		this.time = fs.getFloat();
	}
}