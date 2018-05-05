package cfg.ui;
public final class Keyframe extends cfg.CfgObject {
	public final static int TYPEID = 468147742;
	final public int getTypeId() { return TYPEID; }
	public final float time;
	public final float value;
	public final int tangentMode;
	public final float inTangent;
	public final float outTangent;
	public Keyframe(cfg.DataStream fs) {
		this.time = fs.getFloat();
		this.value = fs.getFloat();
		this.tangentMode = fs.getInt();
		this.inTangent = fs.getFloat();
		this.outTangent = fs.getFloat();
	}
}