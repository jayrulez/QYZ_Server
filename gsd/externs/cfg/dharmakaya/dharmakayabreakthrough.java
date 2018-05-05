package cfg.dharmakaya;
public final class dharmakayabreakthrough extends cfg.CfgObject {
	public final static int TYPEID = 1854303530;
	final public int getTypeId() { return TYPEID; }
	public final int breakthroughlevel;
	public final int upexp;
	public final int dharmakayalevellimit;
	public final float propertyaddrate;
	public dharmakayabreakthrough(cfg.DataStream fs) {
		this.breakthroughlevel = fs.getInt();
		this.upexp = fs.getInt();
		this.dharmakayalevellimit = fs.getInt();
		this.propertyaddrate = fs.getFloat();
	}
}