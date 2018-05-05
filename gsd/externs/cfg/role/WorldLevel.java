package cfg.role;
public final class WorldLevel extends cfg.CfgObject {
	public final static int TYPEID = -990722528;
	final public int getTypeId() { return TYPEID; }
	public final int worldlevelrank;
	public final int fixlevel;
	public final int startlevel;
	public final java.util.List<cfg.role.WorldExpRate> exprate = new java.util.ArrayList<>();
	public WorldLevel(cfg.DataStream fs) {
		this.worldlevelrank = fs.getInt();
		this.fixlevel = fs.getInt();
		this.startlevel = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.exprate.add(new cfg.role.WorldExpRate(fs));
		}
	}
}