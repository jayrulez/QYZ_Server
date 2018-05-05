package cfg.common;
public final class TaskType extends cfg.CfgObject {
	public final static int TYPEID = 1903176088;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String name;
	public final int countlimit;
	public final int resetcounttimerid;
	public TaskType(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.name = fs.getString();
		this.countlimit = fs.getInt();
		this.resetcounttimerid = fs.getInt();
	}
}