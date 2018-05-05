package cfg.plot;
public abstract class PlotTalk extends cfg.CfgObject {
	public final int id;
	public final int talktype;
	public final int namefontsize;
	public final int contentfontsize;
	public final boolean branch;
	public PlotTalk(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.talktype = fs.getInt();
		this.namefontsize = fs.getInt();
		this.contentfontsize = fs.getInt();
		this.branch = fs.getBool();
	}
}