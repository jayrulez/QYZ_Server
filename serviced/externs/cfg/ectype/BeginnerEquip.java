package cfg.ectype;
public final class BeginnerEquip extends cfg.CfgObject {
	public final static int TYPEID = -577828974;
	final public int getTypeId() { return TYPEID; }
	public final int profession;
	public final int weaponid;
	public final int clothid;
	public final int hatid;
	public final int shoesid;
	public final int notweaponanneallevel;
	public final int weaponanneallevel;
	public final int perfuselevel;
	public final int talismanid;
	public final int battlepower;
	public final java.util.List<Integer> skillorder = new java.util.ArrayList<>();
	public BeginnerEquip(cfg.DataStream fs) {
		this.profession = fs.getInt();
		this.weaponid = fs.getInt();
		this.clothid = fs.getInt();
		this.hatid = fs.getInt();
		this.shoesid = fs.getInt();
		this.notweaponanneallevel = fs.getInt();
		this.weaponanneallevel = fs.getInt();
		this.perfuselevel = fs.getInt();
		this.talismanid = fs.getInt();
		this.battlepower = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.skillorder.add(fs.getInt());
		}
	}
}