package cfg.buff;
public final class Reflect extends cfg.buff.Effect {
	public final static int TYPEID = 2047424844;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<Float> reflectrate = new java.util.ArrayList<>();
	public Reflect(cfg.DataStream fs) {
		super(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.reflectrate.add(fs.getFloat());
		}
	}
}