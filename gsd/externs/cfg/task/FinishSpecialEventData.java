package cfg.task;
public final class FinishSpecialEventData extends cfg.CfgObject {
	public final static int TYPEID = -983460995;
	final public int getTypeId() { return TYPEID; }
	public final int eventtype;
	public final int id;
	public final int targetid;
	public final cfg.task.LocationPointData location;
	public final int playcgtype;
	public final int count;
	public final String description;
	public FinishSpecialEventData(cfg.DataStream fs) {
		this.eventtype = fs.getInt();
		this.id = fs.getInt();
		this.targetid = fs.getInt();
		this.location = new cfg.task.LocationPointData(fs);
		this.playcgtype = fs.getInt();
		this.count = fs.getInt();
		this.description = fs.getString();
	}
}