package cfg.ectype;
public final class MissionKillMonster extends cfg.CfgObject {
	public final static int TYPEID = 507461980;
	final public int getTypeId() { return TYPEID; }
	public final int monsterid;
	public final int enviroment;
	public final int count;
	public MissionKillMonster(cfg.DataStream fs) {
		this.monsterid = fs.getInt();
		this.enviroment = fs.getInt();
		this.count = fs.getInt();
	}
}