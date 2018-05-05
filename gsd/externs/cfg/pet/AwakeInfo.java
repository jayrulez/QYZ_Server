package cfg.pet;
public final class AwakeInfo extends cfg.CfgObject {
	public final static int TYPEID = 1153355162;
	final public int getTypeId() { return TYPEID; }
	public final int awakeid;
	public final int requirepetlevel;
	public final int petfragmentcost;
	public final cfg.cmd.condition.XuNiBi requirexunibi;
	public final String displaytext;
	public final java.util.List<cfg.pet.AwakeEffect> effect = new java.util.ArrayList<>();
	public AwakeInfo(cfg.DataStream fs) {
		this.awakeid = fs.getInt();
		this.requirepetlevel = fs.getInt();
		this.petfragmentcost = fs.getInt();
		this.requirexunibi = new cfg.cmd.condition.XuNiBi(fs);
		this.displaytext = fs.getString();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.effect.add((cfg.pet.AwakeEffect)fs.getObject(fs.getString()));
		}
	}
}