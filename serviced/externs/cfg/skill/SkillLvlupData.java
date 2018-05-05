package cfg.skill;
public final class SkillLvlupData extends cfg.CfgObject {
	public final static int TYPEID = 1513496797;
	final public int getTypeId() { return TYPEID; }
	public final int requirelvl;
	public final cfg.cmd.condition.XuNiBi requirecurrency1;
	public final cfg.cmd.condition.ZaoHua requirecurrency2;
	public SkillLvlupData(cfg.DataStream fs) {
		this.requirelvl = fs.getInt();
		this.requirecurrency1 = new cfg.cmd.condition.XuNiBi(fs);
		this.requirecurrency2 = new cfg.cmd.condition.ZaoHua(fs);
	}
}