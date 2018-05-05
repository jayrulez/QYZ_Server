package cfg.guide;
public final class Slider extends cfg.guide.GuideEffect {
	public final static int TYPEID = 393292157;
	final public int getTypeId() { return TYPEID; }
	public final int type;
	public final java.util.List<Float> pos = new java.util.ArrayList<>();
	public final float fromRotation;
	public final float toRotation;
	public Slider(cfg.DataStream fs) {
		super(fs);
		this.type = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.pos.add(fs.getFloat());
		}
		this.fromRotation = fs.getFloat();
		this.toRotation = fs.getFloat();
	}
}