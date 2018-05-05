package cfg.system;
public final class ChatRobotCfg extends cfg.CfgObject {
	public final static int TYPEID = 1464068615;
	final public int getTypeId() { return TYPEID; }
	public final int levelmin;
	public final int levelmax;
	public final int viplevelmin;
	public final int viplevelmax;
	public final int robotnum;
	public final int initdelay;
	public final int chatinterval;
	public ChatRobotCfg(cfg.DataStream fs) {
		this.levelmin = fs.getInt();
		this.levelmax = fs.getInt();
		this.viplevelmin = fs.getInt();
		this.viplevelmax = fs.getInt();
		this.robotnum = fs.getInt();
		this.initdelay = fs.getInt();
		this.chatinterval = fs.getInt();
	}
}