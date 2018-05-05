package cfg.pet;
public final class PetStageStar extends cfg.CfgObject {
	public final static int TYPEID = 772890858;
	final public int getTypeId() { return TYPEID; }
	public final int qualitylevel;
	public final String introduction;
	public final float qualitymaturerate;
	public final int requirepetlvl;
	public final java.util.List<cfg.cmd.condition.Item> requireitem = new java.util.ArrayList<>();
	public final cfg.cmd.condition.XuNiBi requirexunibi;
	public PetStageStar(cfg.DataStream fs) {
		this.qualitylevel = fs.getInt();
		this.introduction = fs.getString();
		this.qualitymaturerate = fs.getFloat();
		this.requirepetlvl = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.requireitem.add(new cfg.cmd.condition.Item(fs));
		}
		this.requirexunibi = new cfg.cmd.condition.XuNiBi(fs);
	}
}