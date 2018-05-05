package cfg.family;
public final class BossInfo extends cfg.CfgObject {
	public final static int TYPEID = 1573009307;
	final public int getTypeId() { return TYPEID; }
	public final int requireexp;
	public final cfg.cmd.condition.MinLevel requipreplayerlvl;
	public final cfg.cmd.condition.MinFamilyLevel requirefamilylvl;
	public final int monsterid;
	public final cfg.cmd.condition.FamilyMoney requirefamilycapital;
	public final cfg.cmd.action.OneItem dropitem;
	public final java.util.List<Integer> dropamount = new java.util.ArrayList<>();
	public final cfg.cmd.action.OneItem luckybonus;
	public final cfg.cmd.action.OneItem lasthitbonus;
	public BossInfo(cfg.DataStream fs) {
		this.requireexp = fs.getInt();
		this.requipreplayerlvl = new cfg.cmd.condition.MinLevel(fs);
		this.requirefamilylvl = new cfg.cmd.condition.MinFamilyLevel(fs);
		this.monsterid = fs.getInt();
		this.requirefamilycapital = new cfg.cmd.condition.FamilyMoney(fs);
		this.dropitem = new cfg.cmd.action.OneItem(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.dropamount.add(fs.getInt());
		}
		this.luckybonus = new cfg.cmd.action.OneItem(fs);
		this.lasthitbonus = new cfg.cmd.action.OneItem(fs);
	}
}