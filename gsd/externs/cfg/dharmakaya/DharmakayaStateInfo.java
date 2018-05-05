package cfg.dharmakaya;
public final class DharmakayaStateInfo extends cfg.CfgObject {
	public final static int TYPEID = 1318900443;
	final public int getTypeId() { return TYPEID; }
	public final int num;
	public final String name;
	public final String decs;
	public final int unlockneedlevel;
	public final java.util.List<cfg.dharmakaya.Property> getproperty = new java.util.ArrayList<>();
	public DharmakayaStateInfo(cfg.DataStream fs) {
		this.num = fs.getInt();
		this.name = fs.getString();
		this.decs = fs.getString();
		this.unlockneedlevel = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.getproperty.add(new cfg.dharmakaya.Property(fs));
		}
	}
}