package cfg.ui;
public final class AnimationCurve extends cfg.CfgObject {
	public final static int TYPEID = 801452923;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<cfg.ui.Keyframe> keys = new java.util.ArrayList<>();
	public final String postWrapMode;
	public final String preWrapMode;
	public AnimationCurve(cfg.DataStream fs) {
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.keys.add(new cfg.ui.Keyframe(fs));
		}
		this.postWrapMode = fs.getString();
		this.preWrapMode = fs.getString();
	}
}