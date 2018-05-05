package cfg.plot;
public final class Cutscene extends cfg.CfgObject {
	public final static int TYPEID = 1972347405;
	final public int getTypeId() { return TYPEID; }
	public final int playmode;
	public final String scriptname;
	public final String scenename;
	public Cutscene(cfg.DataStream fs) {
		this.playmode = fs.getInt();
		this.scriptname = fs.getString();
		this.scenename = fs.getString();
	}
}