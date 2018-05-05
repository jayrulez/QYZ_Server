package cfg.cmd.condition;
public final class LevelNum extends cfg.CfgObject {
	public final static int TYPEID = -2114385165;
	final public int getTypeId() { return TYPEID; }
	public final int level;
	public final int num;
	public LevelNum(cfg.DataStream fs) {
		this.level = fs.getInt();
		this.num = fs.getInt();
	}
}