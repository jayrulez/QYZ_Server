package cfg.equip;
public final class AmuletConfig extends cfg.CfgObject {
	public final static int TYPEID = -236848004;
	final public int getTypeId() { return TYPEID; }
	public static final int AMULET_PROP_COUNT_PER_PAGE = 6;
	public static final int AMULET_PAGE_COUNT = 3;
	public static final int AMULET_DAY_WASH_COUNT_CMD_ID = 0;
	public static final int STATE_UNLOCK = 0;
	public static final int STATE_LOCK = 1;
	public static final int STATE_UN_OPEN = 0;
	public static final int STATE_OPEN = 1;
	public final java.util.List<cfg.cmd.condition.Item> lockcost = new java.util.ArrayList<>();
	public final java.util.List<Integer> expandlevel = new java.util.ArrayList<>();
	public final java.util.List<Integer> qualityjudge = new java.util.ArrayList<>();
	public final java.util.List<String> icon = new java.util.ArrayList<>();
	public final cfg.cmd.condition.VipLimits2 washcost;
	public final int lockitemid;
	public AmuletConfig(cfg.DataStream fs) {
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.lockcost.add(new cfg.cmd.condition.Item(fs));
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.expandlevel.add(fs.getInt());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.qualityjudge.add(fs.getInt());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.icon.add(fs.getString());
		}
		this.washcost = new cfg.cmd.condition.VipLimits2(fs);
		this.lockitemid = fs.getInt();
	}
}