package cfg.pureair;
public final class AirLayInfo extends cfg.CfgObject {
	public final static int TYPEID = 100833742;
	final public int getTypeId() { return TYPEID; }
	public final int lay;
	public final String model;
	public final cfg.cmd.condition.MinLevel rolelvlimit;
	public final int purelvlimit;
	public final int awakelimit;
	public final int starlimit;
	public final java.util.List<cfg.cmd.condition.Condition> upcost = new java.util.ArrayList<>();
	public AirLayInfo(cfg.DataStream fs) {
		this.lay = fs.getInt();
		this.model = fs.getString();
		this.rolelvlimit = new cfg.cmd.condition.MinLevel(fs);
		this.purelvlimit = fs.getInt();
		this.awakelimit = fs.getInt();
		this.starlimit = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.upcost.add((cfg.cmd.condition.Condition)fs.getObject(fs.getString()));
		}
	}
}