package cfg.pureair;
public final class PropertyRate extends cfg.pureair.GetProperty {
	public final static int TYPEID = -1289429797;
	final public int getTypeId() { return TYPEID; }
	public final int gainability;
	public final float rate;
	public PropertyRate(cfg.DataStream fs) {
		super(fs);
		this.gainability = fs.getInt();
		this.rate = fs.getFloat();
	}
}