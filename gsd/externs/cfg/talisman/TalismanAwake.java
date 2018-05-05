package cfg.talisman;
public final class TalismanAwake extends cfg.CfgObject {
	public final static int TYPEID = 168864129;
	final public int getTypeId() { return TYPEID; }
	public static final int AWAKE_COST = 0;
	public final int id;
	public final String name;
	public final java.util.List<cfg.talisman.AwakeInfo> awakeinfo = new java.util.ArrayList<>();
	public TalismanAwake(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.name = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.awakeinfo.add(new cfg.talisman.AwakeInfo(fs));
		}
	}
}