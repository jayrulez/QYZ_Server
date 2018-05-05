package cfg.plot;
public final class PlotGenderConfig extends cfg.CfgObject {
	public final static int TYPEID = -1267342233;
	final public int getTypeId() { return TYPEID; }
	public final cfg.plot.Vector3 cameradeviation;
	public final String rolemodelname;
	public final String rolehandlename;
	public PlotGenderConfig(cfg.DataStream fs) {
		this.cameradeviation = new cfg.plot.Vector3(fs);
		this.rolemodelname = fs.getString();
		this.rolehandlename = fs.getString();
	}
}