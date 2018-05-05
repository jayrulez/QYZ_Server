package cfg.family;
public final class Boss extends cfg.CfgObject {
	public final static int TYPEID = -661598899;
	final public int getTypeId() { return TYPEID; }
	public static final int picktimes = 3;
	public final int bossid;
	public final String name;
	public final String introduction;
	public final int offsety;
	public final int scale;
	public final java.util.List<cfg.family.BossInfo> bossinfo = new java.util.ArrayList<>();
	public Boss(cfg.DataStream fs) {
		this.bossid = fs.getInt();
		this.name = fs.getString();
		this.introduction = fs.getString();
		this.offsety = fs.getInt();
		this.scale = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.bossinfo.add(new cfg.family.BossInfo(fs));
		}
	}
}