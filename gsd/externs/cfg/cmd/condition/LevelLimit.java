package cfg.cmd.condition;
public final class LevelLimit extends cfg.cmd.condition.Condition {
	public final static int TYPEID = -406813720;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<cfg.cmd.condition.LevelNum> levels = new java.util.ArrayList<>();
	public LevelLimit(cfg.DataStream fs) {
		super(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.levels.add(new cfg.cmd.condition.LevelNum(fs));
		}
	}
}