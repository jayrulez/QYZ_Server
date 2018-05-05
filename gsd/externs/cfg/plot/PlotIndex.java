package cfg.plot;
public final class PlotIndex extends cfg.CfgObject {
	public final static int TYPEID = 348057166;
	final public int getTypeId() { return TYPEID; }
	public final String index;
	public final int id;
	public final String displayname;
	public final String introduction;
	public final String mapname;
	public final int mapid;
	public final cfg.plot.CutsceneGroup cutscenegroup;
	public PlotIndex(cfg.DataStream fs) {
		this.index = fs.getString();
		this.id = fs.getInt();
		this.displayname = fs.getString();
		this.introduction = fs.getString();
		this.mapname = fs.getString();
		this.mapid = fs.getInt();
		this.cutscenegroup = new cfg.plot.CutsceneGroup(fs);
	}
}