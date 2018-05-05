package cfg.equip;
public abstract class MainEquip extends cfg.equip.Equip {
	public final cfg.cmd.condition.ProfessionLimit professionlimit;
	public final int nextid;
	public final int extraequipid;
	public final cfg.cmd.condition.XuNiBi upgradecurrencycost;
	public final int carryingitemnum;
	public final float recommendrate;
	public final java.util.List<cfg.equip.EquipPropertyData> property = new java.util.ArrayList<>();
	public MainEquip(cfg.DataStream fs) {
		super(fs);
		this.professionlimit = new cfg.cmd.condition.ProfessionLimit(fs);
		this.nextid = fs.getInt();
		this.extraequipid = fs.getInt();
		this.upgradecurrencycost = new cfg.cmd.condition.XuNiBi(fs);
		this.carryingitemnum = fs.getInt();
		this.recommendrate = fs.getFloat();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.property.add(new cfg.equip.EquipPropertyData(fs));
		}
	}
}