package cfg.plot;
public final class CutsceneGroup extends cfg.CfgObject {
	public final static int TYPEID = 926065778;
	final public int getTypeId() { return TYPEID; }
	public final int mode;
	public final java.util.List<cfg.plot.Cutscene> cutscenes = new java.util.ArrayList<>();
	public CutsceneGroup(cfg.DataStream fs) {
		this.mode = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.cutscenes.add(new cfg.plot.Cutscene(fs));
		}
	}
}