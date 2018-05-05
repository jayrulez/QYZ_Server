package cfg.guide;
public final class NPC extends cfg.guide.GuideEffect {
	public final static int TYPEID = -1630266427;
	final public int getTypeId() { return TYPEID; }
	public final int type;
	public final java.util.List<Float> dialogpos = new java.util.ArrayList<>();
	public final String text;
	public NPC(cfg.DataStream fs) {
		super(fs);
		this.type = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.dialogpos.add(fs.getFloat());
		}
		this.text = fs.getString();
	}
}