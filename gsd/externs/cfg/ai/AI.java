package cfg.ai;
public abstract class AI extends cfg.CfgObject {
	public static final int IDLE_TIME = 10;
	public static final int DEFAULT_UPDATE_INTERVAL = 400;
	public final int id;
	public AI(cfg.DataStream fs) {
		this.id = fs.getInt();
	}
}