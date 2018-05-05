package cfg.setting;
public final class LinearFogSetting extends cfg.CfgObject {
	public final static int TYPEID = 2090842879;
	final public int getTypeId() { return TYPEID; }
	public final int startdistance;
	public final int enddistance;
	public LinearFogSetting(cfg.DataStream fs) {
		this.startdistance = fs.getInt();
		this.enddistance = fs.getInt();
	}
}