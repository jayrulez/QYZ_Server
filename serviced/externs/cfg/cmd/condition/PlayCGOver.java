package cfg.cmd.condition;
public final class PlayCGOver extends cfg.cmd.condition.Condition {
	public final static int TYPEID = 486567133;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<Integer> cgids = new java.util.ArrayList<>();
	public PlayCGOver(cfg.DataStream fs) {
		super(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.cgids.add(fs.getInt());
		}
	}
}