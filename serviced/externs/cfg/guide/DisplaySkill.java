package cfg.guide;
public final class DisplaySkill extends cfg.guide.GuideEffect {
	public final static int TYPEID = 632469067;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<Integer> desc = new java.util.ArrayList<>();
	public DisplaySkill(cfg.DataStream fs) {
		super(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.desc.add(fs.getInt());
		}
	}
}