package cfg.pureair;
public final class AirAwakeInfo extends cfg.CfgObject {
	public final static int TYPEID = 1645195823;
	final public int getTypeId() { return TYPEID; }
	public final int awakelevel;
	public final int showawakelevel;
	public final int laynumber;
	public final java.util.List<cfg.cmd.condition.Condition> cost = new java.util.ArrayList<>();
	public final float successrate;
	public final cfg.pureair.LuckyValue luckyvalue;
	public final java.util.List<cfg.pureair.GetProperty> getproperty = new java.util.ArrayList<>();
	public AirAwakeInfo(cfg.DataStream fs) {
		this.awakelevel = fs.getInt();
		this.showawakelevel = fs.getInt();
		this.laynumber = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.cost.add((cfg.cmd.condition.Condition)fs.getObject(fs.getString()));
		}
		this.successrate = fs.getFloat();
		this.luckyvalue = new cfg.pureair.LuckyValue(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.getproperty.add((cfg.pureair.GetProperty)fs.getObject(fs.getString()));
		}
	}
}