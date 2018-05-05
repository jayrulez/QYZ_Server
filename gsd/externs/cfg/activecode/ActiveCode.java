package cfg.activecode;
public final class ActiveCode extends cfg.CfgObject {
	public final static int TYPEID = 2042015492;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final java.util.List<String> file = new java.util.ArrayList<>();
	public final int groupid;
	public final int daylimit;
	public final int totallimit;
	public final cfg.cmd.condition.MinMaxLevel levellimit;
	public final cfg.cmd.action.Bonus bonus;
	public ActiveCode(cfg.DataStream fs) {
		this.id = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.file.add(fs.getString());
		}
		this.groupid = fs.getInt();
		this.daylimit = fs.getInt();
		this.totallimit = fs.getInt();
		this.levellimit = new cfg.cmd.condition.MinMaxLevel(fs);
		this.bonus = (cfg.cmd.action.Bonus)fs.getObject(fs.getString());
	}
}