package cfg.task;
public final class SkillInfo extends cfg.CfgObject {
	public final static int TYPEID = 1259183136;
	final public int getTypeId() { return TYPEID; }
	public final int skillid;
	public final int skilllevel;
	public SkillInfo(cfg.DataStream fs) {
		this.skillid = fs.getInt();
		this.skilllevel = fs.getInt();
	}
}