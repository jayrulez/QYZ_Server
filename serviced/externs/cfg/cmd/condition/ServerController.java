package cfg.cmd.condition;
public final class ServerController extends cfg.cmd.condition.Condition {
	public final static int TYPEID = -320362544;
	final public int getTypeId() { return TYPEID; }
	public final int controllerid;
	public ServerController(cfg.DataStream fs) {
		super(fs);
		this.controllerid = fs.getInt();
	}
}