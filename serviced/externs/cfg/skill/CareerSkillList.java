package cfg.skill;
public final class CareerSkillList extends cfg.CfgObject {
	public final static int TYPEID = -1394439958;
	final public int getTypeId() { return TYPEID; }
	public final int career;
	public final int normalskillid;
	public final java.util.List<Integer> skilllist = new java.util.ArrayList<>();
	public CareerSkillList(cfg.DataStream fs) {
		this.career = fs.getInt();
		this.normalskillid = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.skilllist.add(fs.getInt());
		}
	}
}