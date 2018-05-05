package cfg.dharmakaya;
public final class DharmakayaLevelInfo extends cfg.CfgObject {
	public final static int TYPEID = 1748564046;
	final public int getTypeId() { return TYPEID; }
	public final int level;
	public final int convertlevel;
	public final java.util.List<cfg.cmd.condition.Condition> normalupcost = new java.util.ArrayList<>();
	public final java.util.List<cfg.cmd.condition.Condition> advupcost = new java.util.ArrayList<>();
	public final java.util.List<cfg.dharmakaya.Property> getproperty = new java.util.ArrayList<>();
	public DharmakayaLevelInfo(cfg.DataStream fs) {
		this.level = fs.getInt();
		this.convertlevel = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.normalupcost.add((cfg.cmd.condition.Condition)fs.getObject(fs.getString()));
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.advupcost.add((cfg.cmd.condition.Condition)fs.getObject(fs.getString()));
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.getproperty.add(new cfg.dharmakaya.Property(fs));
		}
	}
}