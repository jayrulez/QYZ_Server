package cfg.arena;
public final class ArenaRankStep extends cfg.CfgObject {
	public final static int TYPEID = -964705810;
	final public int getTypeId() { return TYPEID; }
	public final int upbound;
	public final java.util.List<cfg.arena.ArenaRivalBound> rivalbounds = new java.util.ArrayList<>();
	public ArenaRankStep(cfg.DataStream fs) {
		this.upbound = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.rivalbounds.add(new cfg.arena.ArenaRivalBound(fs));
		}
	}
}