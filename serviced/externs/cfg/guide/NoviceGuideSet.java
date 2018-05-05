package cfg.guide;
public final class NoviceGuideSet extends cfg.CfgObject {
	public final static int TYPEID = 1708169752;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final java.util.List<Integer> subset = new java.util.ArrayList<>();
	public NoviceGuideSet(cfg.DataStream fs) {
		this.id = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.subset.add(fs.getInt());
		}
	}
}