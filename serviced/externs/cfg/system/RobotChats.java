package cfg.system;
public final class RobotChats extends cfg.CfgObject {
	public final static int TYPEID = -198154746;
	final public int getTypeId() { return TYPEID; }
	public final int id;
	public final String content;
	public final int gender;
	public RobotChats(cfg.DataStream fs) {
		this.id = fs.getInt();
		this.content = fs.getString();
		this.gender = fs.getInt();
	}
}