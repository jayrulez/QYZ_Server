package cfg.task;
public final class CallNpcInfo extends cfg.CfgObject {
	public final static int TYPEID = -1331384270;
	final public int getTypeId() { return TYPEID; }
	public final int npcid;
	public final cfg.task.LocationPointData callnpclocation;
	public final int callnpclifetime;
	public final int lifetime;
	public CallNpcInfo(cfg.DataStream fs) {
		this.npcid = fs.getInt();
		this.callnpclocation = new cfg.task.LocationPointData(fs);
		this.callnpclifetime = fs.getInt();
		this.lifetime = fs.getInt();
	}
}