package cfg.ectype;
public final class WorldBoss extends cfg.CfgObject {
	public final static int TYPEID = -1459565389;
	final public int getTypeId() { return TYPEID; }
	public static final int COINPOINT_OPEN_LEVEL = 30;
	public static final float LINE_PARA = 0.5f;
	public static final int TALK_INTERVAL = 5;
	public static final int TALK_LAST = 5;
	public final int id;
	public final int monsterid;
	public final int mapid;
	public final cfg.map.Vector2 position;
	public final int fightforce;
	public final java.util.List<Integer> showbonusid = new java.util.ArrayList<>();
	public final String bosstalk;
	public final String bossdes;
	public final cfg.ectype.Broadcast prebroadcast;
	public final cfg.ectype.Broadcast openbroadcast;
	public final cfg.ectype.Broadcast ongoingbroadcast;
	public final cfg.ectype.Broadcast killbroadcast;
	public final cfg.ectype.Broadcast failbroadcast;
	public final int beforeopenbroadcasttime;
	public final int afteropenbroadcasttime;
	public final int afteropenbroadcastinterval;
	public final int endtime;
	public final java.util.List<cfg.ectype.TimeControler> opentimes = new java.util.ArrayList<>();
	public final float localposx;
	public final float localposy;
	public final float scale;
	public WorldBoss(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.monsterid = fs.getInt();
		this.mapid = fs.getInt();
		this.position = new cfg.map.Vector2(fs);
		this.fightforce = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.showbonusid.add(fs.getInt());
		}
		this.bosstalk = fs.getString();
		this.bossdes = fs.getString();
		this.prebroadcast = new cfg.ectype.Broadcast(fs);
		this.openbroadcast = new cfg.ectype.Broadcast(fs);
		this.ongoingbroadcast = new cfg.ectype.Broadcast(fs);
		this.killbroadcast = new cfg.ectype.Broadcast(fs);
		this.failbroadcast = new cfg.ectype.Broadcast(fs);
		this.beforeopenbroadcasttime = fs.getInt();
		this.afteropenbroadcasttime = fs.getInt();
		this.afteropenbroadcastinterval = fs.getInt();
		this.endtime = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.opentimes.add(new cfg.ectype.TimeControler(fs));
		}
		this.localposx = fs.getFloat();
		this.localposy = fs.getFloat();
		this.scale = fs.getFloat();
	}
}