package cfg.buff;
public final class AddPropertyByLevel extends cfg.buff.Effect {
	public final static int TYPEID = -996297464;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<String> introduction = new java.util.ArrayList<>();
	public final int property;
	public final java.util.List<Float> value = new java.util.ArrayList<>();
	public final int maxoverlay;
	public AddPropertyByLevel(cfg.DataStream fs) {
		super(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.introduction.add(fs.getString());
		}
		this.property = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.value.add(fs.getFloat());
		}
		this.maxoverlay = fs.getInt();
	}
}