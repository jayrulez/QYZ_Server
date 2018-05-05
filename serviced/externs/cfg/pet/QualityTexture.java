package cfg.pet;
public final class QualityTexture extends cfg.CfgObject {
	public final static int TYPEID = 484515253;
	final public int getTypeId() { return TYPEID; }
	public final java.util.Map<Integer, String> qualitytexture = new java.util.HashMap<>();
	public QualityTexture(cfg.DataStream fs) {
		for(int n = fs.getInt(); n-- > 0 ; ) {
			final int _k = fs.getInt();
			this.qualitytexture.put(_k, fs.getString());
		}
	}
}