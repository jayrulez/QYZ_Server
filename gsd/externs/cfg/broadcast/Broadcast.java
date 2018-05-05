package cfg.broadcast;
public final class Broadcast extends cfg.CfgObject {
	public final static int TYPEID = 1810297642;
	final public int getTypeId() { return TYPEID; }
	public final java.util.List<cfg.broadcast.Notice> notices = new java.util.ArrayList<>();
	public Broadcast(cfg.DataStream fs) {
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.notices.add(new cfg.broadcast.Notice(fs));
		}
	}
}