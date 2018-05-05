package cfg.map;
public final class MapLoading extends cfg.CfgObject {
	public final static int TYPEID = 1257061116;
	final public int getTypeId() { return TYPEID; }
	public final String texturename;
	public final java.util.List<String> texts = new java.util.ArrayList<>();
	public MapLoading(cfg.DataStream fs) {
		this.texturename = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.texts.add(fs.getString());
		}
	}
}