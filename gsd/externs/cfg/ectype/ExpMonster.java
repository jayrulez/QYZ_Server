package cfg.ectype;
public final class ExpMonster extends cfg.CfgObject {
	public final static int TYPEID = 1575379689;
	final public int getTypeId() { return TYPEID; }
	public final String decs;
	public final cfg.ectype.Broadcast openbroadcast;
	public final cfg.ectype.Broadcast ongoingbroadcast;
	public final cfg.ectype.Broadcast endbroadcast;
	public final int beforeopenbroadcasttime;
	public final int afteropenbroadcasttime;
	public final int afteropenbroadcastinterval;
	public final int lasttime;
	public final java.util.List<cfg.ectype.TimeControler> opentimes = new java.util.ArrayList<>();
	public final int refreshtime;
	public final int id;
	public final cfg.cmd.condition.MinLevel minlevel;
	public final java.util.List<Integer> dungeonid = new java.util.ArrayList<>();
	public final java.util.List<cfg.ectype.ExpMonsterMsg> monstermsg = new java.util.ArrayList<>();
	public ExpMonster(cfg.DataStream fs) {
		this.decs = fs.getString();
		this.openbroadcast = new cfg.ectype.Broadcast(fs);
		this.ongoingbroadcast = new cfg.ectype.Broadcast(fs);
		this.endbroadcast = new cfg.ectype.Broadcast(fs);
		this.beforeopenbroadcasttime = fs.getInt();
		this.afteropenbroadcasttime = fs.getInt();
		this.afteropenbroadcastinterval = fs.getInt();
		this.lasttime = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.opentimes.add(new cfg.ectype.TimeControler(fs));
		}
		this.refreshtime = fs.getInt();
		this.id = fs.getInt();
		this.minlevel = new cfg.cmd.condition.MinLevel(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.dungeonid.add(fs.getInt());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.monstermsg.add(new cfg.ectype.ExpMonsterMsg(fs));
		}
	}
}