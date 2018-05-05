package cfg.setting;
public final class GraphicSetting extends cfg.CfgObject {
	public final static int TYPEID = -712596848;
	final public int getTypeId() { return TYPEID; }
	public final int camerafarclip;
	public final java.util.List<Integer> scenestriplist = new java.util.ArrayList<>();
	public final java.util.List<Integer> hideeffectlist = new java.util.ArrayList<>();
	public final boolean hideothernamehp;
	public final java.util.List<Integer> hideflytextlist = new java.util.ArrayList<>();
	public final java.util.Map<Integer, Integer> loddistancemap = new java.util.HashMap<>();
	public final int fogsettingid;
	public GraphicSetting(cfg.DataStream fs) {
		this.camerafarclip = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.scenestriplist.add(fs.getInt());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.hideeffectlist.add(fs.getInt());
		}
		this.hideothernamehp = fs.getBool();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.hideflytextlist.add(fs.getInt());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final int _k = fs.getInt();
			this.loddistancemap.put(_k, fs.getInt());
		}
		this.fogsettingid = fs.getInt();
	}
}