package cfg.pureair;
public final class SublimeInfo extends cfg.CfgObject {
	public final static int TYPEID = -236537059;
	final public int getTypeId() { return TYPEID; }
	public final int starlevel;
	public final String showstarlevel;
	public final int laynumber;
	public final int purelvlimit;
	public final int awakelimit;
	public final java.util.List<cfg.cmd.condition.Condition> cost = new java.util.ArrayList<>();
	public final java.util.List<cfg.pureair.GetProperty> getproperty = new java.util.ArrayList<>();
	public SublimeInfo(cfg.DataStream fs) {
		this.starlevel = fs.getInt();
		this.showstarlevel = fs.getString();
		this.laynumber = fs.getInt();
		this.purelvlimit = fs.getInt();
		this.awakelimit = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.cost.add((cfg.cmd.condition.Condition)fs.getObject(fs.getString()));
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.getproperty.add((cfg.pureair.GetProperty)fs.getObject(fs.getString()));
		}
	}
}