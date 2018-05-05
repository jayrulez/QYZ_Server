package cfg.team;
public final class team extends cfg.CfgObject {
	public final static int TYPEID = 226349636;
	final public int getTypeId() { return TYPEID; }
	public static final int ReFollowTime = 1;
	public static final int FollowDistance = 2;
	public final int requirelevel;
	public final int teammembermaxcount;
	public final int findnearbyteammaxcount;
	public final int findnearbyplayermaxcount;
	public final int findradius;
	public team(cfg.DataStream fs) {
		this.requirelevel = fs.getInt();
		this.teammembermaxcount = fs.getInt();
		this.findnearbyteammaxcount = fs.getInt();
		this.findnearbyplayermaxcount = fs.getInt();
		this.findradius = fs.getInt();
	}
}