package cfg.pureair;
public final class AirLevel extends cfg.CfgObject {
	public final static int TYPEID = -857877536;
	final public int getTypeId() { return TYPEID; }
	public final int level;
	public final int showlevel;
	public final int laynumber;
	public final java.util.List<cfg.cmd.condition.Condition> cost = new java.util.ArrayList<>();
	public final float successrate;
	public final cfg.pureair.LuckyValue luckyvalue;
	public final java.util.List<cfg.pureair.GetProperty> getproperty = new java.util.ArrayList<>();
	public AirLevel(cfg.DataStream fs) {
		this.level = fs.getInt();
		this.showlevel = fs.getInt();
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