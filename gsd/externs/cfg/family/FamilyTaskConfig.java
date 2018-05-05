package cfg.family;
public final class FamilyTaskConfig extends cfg.CfgObject {
	public final static int TYPEID = 1581037963;
	final public int getTypeId() { return TYPEID; }
	public final int openlevel;
	public final int nextliblevel;
	public final int circletaskamount;
	public final cfg.cmd.condition.DayLimit daycirclelimit;
	public final int weekbonus;
	public final int weekalert;
	public final int refreshtime;
	public final cfg.cmd.condition.YuanBao completecost;
	public final java.util.List<Float> taskbonusrate = new java.util.ArrayList<>();
	public final java.util.List<Float> circlebonusrate = new java.util.ArrayList<>();
	public final int npcid;
	public final int simpleclearamount;
	public final java.util.List<String> tasktalk = new java.util.ArrayList<>();
	public FamilyTaskConfig(cfg.DataStream fs) {
		this.openlevel = fs.getInt();
		this.nextliblevel = fs.getInt();
		this.circletaskamount = fs.getInt();
		this.daycirclelimit = new cfg.cmd.condition.DayLimit(fs);
		this.weekbonus = fs.getInt();
		this.weekalert = fs.getInt();
		this.refreshtime = fs.getInt();
		this.completecost = new cfg.cmd.condition.YuanBao(fs);
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.taskbonusrate.add(fs.getFloat());
		}
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.circlebonusrate.add(fs.getFloat());
		}
		this.npcid = fs.getInt();
		this.simpleclearamount = fs.getInt();
		for(int n = fs.getInt(); n-- > 0 ; ) {
			this.tasktalk.add(fs.getString());
		}
	}
}