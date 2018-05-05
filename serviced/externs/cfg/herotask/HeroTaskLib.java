package cfg.herotask;
public final class HeroTaskLib extends cfg.CfgObject {
	public final static int TYPEID = 1305057697;
	final public int getTypeId() { return TYPEID; }
	public final int level;
	public final java.util.List<cfg.herotask.TaskLibInfo> taskinfo = new java.util.ArrayList<>();
	public HeroTaskLib(cfg.DataStream fs) {
		this.level = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.taskinfo.add(new cfg.herotask.TaskLibInfo(fs));
		}
	}
}