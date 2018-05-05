package cfg.setting;
public final class FogSetting extends cfg.CfgObject {
	public final static int TYPEID = -197292038;
	final public int getTypeId() { return TYPEID; }
	public final boolean enable;
	public final int mode;
	public final float density;
	public final int colorred;
	public final int colorgreen;
	public final int colorblue;
	public final int coloralpha;
	public final int startdistance;
	public final int enddistance;
	public FogSetting(cfg.DataStream fs) {
		this.enable = fs.getBool();
		this.mode = fs.getInt();
		this.density = fs.getFloat();
		this.colorred = fs.getInt();
		this.colorgreen = fs.getInt();
		this.colorblue = fs.getInt();
		this.coloralpha = fs.getInt();
		this.startdistance = fs.getInt();
		this.enddistance = fs.getInt();
	}
}