package cfg.family.citywar;
public final class CityInfo extends cfg.CfgObject {
	public final static int TYPEID = -1239522358;
	final public int getTypeId() { return TYPEID; }
	public final int cityid;
	public final String name;
	public final String listitemname;
	public final int level;
	public final java.util.List<Integer> conectcity = new java.util.ArrayList<>();
	public CityInfo(cfg.DataStream fs) {
		this.cityid = fs.getInt();
		this.name = fs.getString();
		this.listitemname = fs.getString();
		this.level = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.conectcity.add(fs.getInt());
		}
	}
}